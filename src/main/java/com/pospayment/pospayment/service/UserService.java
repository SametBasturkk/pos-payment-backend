package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.Company;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.repository.UserRepo;
import com.pospayment.pospayment.util.Hasher;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    private Hasher hasher;

    private JwtToken jwtToken;

    public UserService(UserRepo userRepo, Hasher hasher, JwtToken jwtToken) {
        this.userRepo = userRepo;
        this.hasher = hasher;
        this.jwtToken = jwtToken;
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
}

