package com.pospayment.pospayment.controller;

import com.pospayment.pospayment.dto.UserDTO;
import com.pospayment.pospayment.exception.TokenException;
import com.pospayment.pospayment.model.User;
import com.pospayment.pospayment.service.UserService;
import com.pospayment.pospayment.util.Converter;
import com.pospayment.pospayment.util.JwtToken;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    private JwtToken jwtToken;

    private Converter converter;

    public AdminController(UserService userService, JwtToken jwtToken, Converter converter) {
        this.userService = userService;
        this.jwtToken = jwtToken;
        this.converter = converter;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody User userRequest) {
        userService.saveUser(userRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userRequest) {
        if (userService.loginUser(userRequest)) {
            String token = jwtToken.createToken(userRequest.getUsername());
            return ResponseEntity.ok().body(token);
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

    @GetMapping("/user-details")
    public ResponseEntity<UserDTO> getUserDetails(@RequestHeader String Authorization) throws TokenException {
        String username = jwtToken.getUsername(Authorization);
        return ResponseEntity.ok(converter.convertToDTO(userService.getUserDetails(username), UserDTO.class));
    }

    @PostMapping("/forgot-password-mail")
    public ResponseEntity<String> forgotPasswordMail(@RequestParam String email) {
        if (userService.forgotPasswordMail(email)) {
            return ResponseEntity.ok("Password reset link sent to email");
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String token, @RequestParam String password) {
        userService.forgotPassword(token, password);
        return ResponseEntity.ok("Password reset successfully");
    }
}
