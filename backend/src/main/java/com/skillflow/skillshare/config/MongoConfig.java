package com.skillflow.skillshare.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PreDestroy;

@Configuration
public class MongoConfig {

    private MongoClient mongoClient;

    @Bean
    public MongoClient mongoClient() {
        mongoClient = MongoClients.create("mongodb+srv://linarasenarathna2001:9iJufuNRx7uUTn1D@cluster0.ttivuhw.mongodb.net/skillflow?retryWrites=true&w=majority");
        return mongoClient;
    }

    @PreDestroy
    public void closeMongoClient() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}