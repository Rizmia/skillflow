// package com.skillflow.skillshare.service;

// import com.skillflow.skillshare.model.GroupUser;
// import com.skillflow.skillshare.repository.GroupUserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class GroupUserService {

//     @Autowired
//     private GroupUserRepository repo;

//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;

//     public GroupUser registerUser(GroupUser user) {
//         if (repo.findByEmail(user.getEmail()) != null) {
//             throw new IllegalArgumentException("User with this email already exists.");
//         }
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         return repo.save(user);
//     }

//     public GroupUser authenticateUser(String email, String password) {
//         GroupUser user = repo.findByEmail(email);
//         if (user != null && passwordEncoder.matches(password, user.getPassword())) {
//             return user;
//         }
//         return null;
//     }
// }