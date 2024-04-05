package com.pospayment.pospayment.service;

import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.repositroy.UserRepo;
import com.pospayment.pospayment.util.Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private Hasher hasher;

    public void saveUser(User user) {
        user.setPassword(hasher.hashPassword(user.getPassword()));
        userRepo.save(user);
    }


    public boolean loginUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb == null) {
            return false;
        }
        return hasher.comparePasswords(user.getPassword(), userFromDb.getPassword());
    }
}

