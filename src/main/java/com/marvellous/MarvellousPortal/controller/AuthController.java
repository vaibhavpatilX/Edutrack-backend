package com.marvellous.MarvellousPortal.controller;

import com.marvellous.MarvellousPortal.Entity.UserAccount;
import com.marvellous.MarvellousPortal.service.AuthService;
import com.marvellous.MarvellousPortal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAccount user) {
        try {
            Map<String, Object> res = authService.register(user);
            // Send welcome email
            try {
                emailService.sendWelcomeEmail(user.getEmail(), user.getName(), user.getRole());
            } catch (Exception ignored) {}
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            Map<String, Object> res = authService.login(
                    body.get("username"), body.get("password")
            );
            return ResponseEntity.ok(res);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}