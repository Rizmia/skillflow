package com.skillflow.skillshare.service;

import com.skillflow.skillshare.dto.RegisterRequest;
import com.skillflow.skillshare.model.User;
import com.skillflow.skillshare.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        Set<String> roles = new HashSet<>();
        roles.add("USER");
        user.setRoles(roles);
        return userRepository.save(user);
    }

    public User createOrUpdateOAuthUser(String email, String username, String oauthId, String oauthProvider) {
        User user = userRepository.findByOauthIdAndOauthProvider(oauthId, oauthProvider)
                .orElse(new User());
        user.setEmail(email);
        user.setUsername(username);
        user.setOauthId(oauthId);
        user.setOauthProvider(oauthProvider);
        if (user.getRoles() == null) {
            Set<String> roles = new HashSet<>();
            roles.add("USER");
            user.setRoles(roles);
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}