// package com.skillflow.skillshare.controller;

// import com.skillflow.skillshare.model.GroupUser;
// import com.skillflow.skillshare.service.GroupUserService;
// import com.skillflow.skillshare.config.JwtUtil;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/users")
// @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST}, allowedHeaders = "*")
// public class GroupUserController {

//     @Autowired
//     private GroupUserService userService;

//     @Autowired
//     private JwtUtil jwtUtil;

//     @PostMapping("/register")
//     public ResponseEntity<?> registerUser(@RequestBody GroupUser user) {
//         try {
//             if (user.getEmail() == null || user.getEmail().trim().isEmpty() || user.getPassword() == null || user.getPassword().trim().isEmpty()) {
//                 return ResponseEntity.badRequest().body("Email and password are required.");
//             }
//             GroupUser registeredUser = userService.registerUser(user);
//             String token = jwtUtil.generateToken(registeredUser.getEmail());
//             return ResponseEntity.ok(new AuthResponse(token, registeredUser.getEmail()));
//         } catch (IllegalArgumentException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering user: " + e.getMessage());
//         }
//     }

//     @PostMapping("/login")
//     public ResponseEntity<?> loginUser(@RequestBody GroupUser user) {
//         try {
//             GroupUser authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
//             if (authenticatedUser == null) {
//                 return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
//             }
//             String token = jwtUtil.generateToken(authenticatedUser.getEmail());
//             return ResponseEntity.ok(new AuthResponse(token, authenticatedUser.getEmail()));
//         } catch (Exception e) {
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error logging in: " + e.getMessage());
//         }
//     }

//     static class AuthResponse {
//         private String token;
//         private String email;

//         public AuthResponse(String token, String email) {
//             this.token = token;
//             this.email = email;
//         }

//         public String getToken() { return token; }
//         public void setToken(String token) { this.token = token; }
//         public String getEmail() { return email; }
//         public void setEmail(String email) { this.email = email; }
//     }
// }