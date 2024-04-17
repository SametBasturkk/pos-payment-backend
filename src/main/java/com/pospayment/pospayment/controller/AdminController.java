package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtToken jwtToken;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User userRequest) {
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userRequest) {
        if (userService.loginUser(userRequest)) {
            String token = jwtToken.createToken(userRequest.getUsername());
            return ResponseEntity.ok().header("Authorization", token).body("Login successful");
        } else {
            return ResponseEntity.status(401).body("Login failed");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestHeader String Authorization, @RequestParam String newPass, @RequestParam String oldPass) throws TokenException {
        jwtToken.validateToken(Authorization);
        userService.resetPassword(Authorization, newPass, oldPass);
        return ResponseEntity.ok("Password reset successfully");
    }
}
