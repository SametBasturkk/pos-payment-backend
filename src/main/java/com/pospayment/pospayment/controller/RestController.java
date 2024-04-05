package com.pospayment.pospayment.controller;

import com.nimbusds.oauth2.sdk.Response;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User userRequest) {
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userRequest) {
        if (userService.loginUser(userRequest)) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }
}
