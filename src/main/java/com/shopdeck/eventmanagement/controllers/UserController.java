package com.shopdeck.eventmanagement.controllers;

import com.shopdeck.eventmanagement.models.User;
import com.shopdeck.eventmanagement.repository.UserRepository;
import com.shopdeck.eventmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) return ResponseEntity.badRequest().body("User exists");
        user.setPassword(encoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        User user = userRepository.findByEmail(payload.get("email")).orElse(null);
        if (user != null && encoder.matches(payload.get("password"), user.getPassword())) {
            return ResponseEntity.ok(jwtUtil.generateToken(user.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}

