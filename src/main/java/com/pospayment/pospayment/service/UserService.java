package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.repository.UserRepo;
import com.pospayment.pospayment.util.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;

@Service
public class UserService {

    private UserRepo userRepo;

    private Hasher hasher;

    private JwtToken jwtToken;

    private Converter converter;

    private Redis redis;

    private CategoryService categoryService;

    private OrderService orderService;

    private ProductService productService;


    public UserService(UserRepo userRepo, Hasher hasher, JwtToken jwtToken, Converter converter, Redis redis, @Lazy CategoryService categoryService, @Lazy OrderService orderService, @Lazy ProductService productService) {
        this.userRepo = userRepo;
        this.hasher = hasher;
        this.jwtToken = jwtToken;
        this.converter = converter;
        this.redis = redis;
        this.categoryService = categoryService;
        this.orderService = orderService;
        this.productService = productService;
    }

    public void saveUser(User user) {
        user.setPassword(hasher.hashPassword(user.getPassword()));
        userRepo.save(user);
    }

    public Company getCompany(String username) {
        User user = userRepo.findByUsername(username);
        return user.getCompany();
    }


    public boolean loginUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb == null) {
            return false;
        }
        return hasher.comparePasswords(user.getPassword(), userFromDb.getPassword());
    }

    public void resetPassword(String token, String newPass, String oldPass) {
        String userName = jwtToken.getUsername(token);
        User user = userRepo.findByUsername(userName);
        if (hasher.comparePasswords(oldPass, user.getPassword())) {
            user.setPassword(hasher.hashPassword(newPass));
            userRepo.save(user);
        }
    }

    public User getUserDetails(String username) {
        return userRepo.findByUsername(username);
    }

    public Boolean forgotPasswordMail(String email) {

        User user = userRepo.findByEmail(email);

        ResetPasswordSchema schema = new ResetPasswordSchema();
        schema.setId(user.getId());
        schema.setExp(new Timestamp(System.currentTimeMillis()));

        if (user != null) {
            redis.set(UUID.randomUUID().toString(), converter.objectToJson(schema));
            //TODO = send mail
            return true;
        } else {
            return false;
        }
    }

    public void forgotPassword(String token, String password) {

        String schema = redis.get(token);
        ResetPasswordSchema resetPasswordSchema = converter.jsonToObject(schema, ResetPasswordSchema.class);
        User user = userRepo.findById(String.valueOf(resetPasswordSchema.getId())).get();
        user.setPassword(hasher.hashPassword(password));
        userRepo.save(user);


    }

    public String getOverview(String username) {
        HashMap<String, Integer> overview = new HashMap<>();
        User user = userRepo.findByUsername(username);
        overview.put("totalProducts", productService.getAllProducts(user.getCompany()).size());
        overview.put("totalCategories", categoryService.getCategoryList(username).size());
        overview.put("totalOrders", orderService.getAllOrders(user.getCompany()).size());

        return converter.convertToJson(overview);
    }
}

