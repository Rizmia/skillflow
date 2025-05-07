package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.User;
import com.skillflow.skillshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{userId}/profile")
    public ResponseEntity<?> getProfile(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable String userId, @RequestBody User update) {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!user.getEmail().equals(currentUserEmail)) {
            throw new RuntimeException("Unauthorized");
        }
        user.setUsername(update.getUsername());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<?> followUser(@PathVariable String userId) {
        // Implement follow logic here
        return ResponseEntity.ok("Followed user: " + userId);
    }

    @DeleteMapping("/{userId}/follow")
    public ResponseEntity<?> unfollowUser(@PathVariable String userId) {
        // Implement unfollow logic here
        return ResponseEntity.ok("Unfollowed user: " + userId);
    }
}