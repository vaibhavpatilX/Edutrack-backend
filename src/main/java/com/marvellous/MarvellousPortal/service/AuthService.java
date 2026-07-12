package com.marvellous.MarvellousPortal.service;

import com.marvellous.MarvellousPortal.Entity.UserAccount;
import com.marvellous.MarvellousPortal.Repository.UserRepository;
import com.marvellous.MarvellousPortal.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthService {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder encoder;
    @Autowired private JwtUtil jwtUtil;

    public Map<String, Object> register(UserAccount user) {
        if (userRepo.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepo.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        UserAccount saved = userRepo.save(user);
        String token = jwtUtil.generateToken(saved.getUsername(), saved.getRole(), saved.getId());
        return buildResponse(saved, token);
    }

    public Map<String, Object> login(String username, String password) {
        UserAccount user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        if (!user.isActive()) {
            throw new RuntimeException("Account is deactivated");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole(), user.getId());
        return buildResponse(user, token);
    }

    private Map<String, Object> buildResponse(UserAccount user, String token) {
        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("id", user.getId());
        res.put("username", user.getUsername());
        res.put("name", user.getName());
        res.put("email", user.getEmail());
        res.put("role", user.getRole());
        res.put("studentId", user.getStudentId());
        res.put("batchId", user.getBatchId());
        return res;
    }
}