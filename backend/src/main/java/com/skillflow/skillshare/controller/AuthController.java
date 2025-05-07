package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.dto.JwtResponse;
import com.skillflow.skillshare.dto.LoginRequest;
import com.skillflow.skillshare.dto.RegisterRequest;
import com.skillflow.skillshare.model.User;
import com.skillflow.skillshare.service.UserService;
import com.skillflow.skillshare.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.registerUser(request);
        String jwt = jwtUtil.generateToken(user.getEmail(), user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getUsername(), user.getRoles()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String jwt = jwtUtil.generateToken(user.getEmail(), user.getUsername(), user.getRoles());
        return ResponseEntity.ok(new JwtResponse(jwt, user.getEmail(), user.getUsername(), user.getRoles()));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok("Successfully logged out. See you soon!");
    }
}