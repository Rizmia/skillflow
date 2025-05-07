package com.skillflow.skillshare.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String email;
    private String username;
    private Set<String> roles;

    public JwtResponse(String token, String email, String username, Set<String> roles) {
        this.token = token;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }
}