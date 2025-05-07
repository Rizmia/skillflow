package com.skillflow.skillshare.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String username;
    private Set<String> roles;
    private String oauthProvider;
    private String oauthId;
}