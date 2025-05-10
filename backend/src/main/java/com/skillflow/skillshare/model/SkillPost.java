package com.skillflow.skillshare.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "skillposts")
public class SkillPost {

    @Id
    private String id;

    private String title;
    private String description;

    private int likes;
    private List<String> comments;

    public SkillPost() {

        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    public SkillPost(String title, String description) {
        this.title = title;
        this.description = description;

        this.likes = 0;
        this.comments = new ArrayList<>();
    }

    // add Getters and Setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }
}
