package com.skillflow.skillshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@CrossOrigin(origins = "*") // Allow all origins for testing purposes, adjust as needed
public class SkillShareApplication {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
            .directory("F:/3rd year 2nd sem/PAF/PAF-Git/skillflow/backend") // specify path here
            .load();

            System.setProperty("GOOGLE_CLIENT_ID", dotenv.get("GOOGLE_CLIENT_ID"));
            System.setProperty("GOOGLE_CLIENT_SECRET", dotenv.get("GOOGLE_CLIENT_SECRET"));

            System.setProperty("JWT_SECRET", dotenv.get("JWT_SECRET"));

            System.setProperty("GITHUB_CLIENT_ID", dotenv.get("GITHUB_CLIENT_ID"));
            System.setProperty("GITHUB_CLIENT_SECRET", dotenv.get("GITHUB_CLIENT_SECRET"));

        SpringApplication.run(SkillShareApplication.class, args);
    }
}

