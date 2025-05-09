package com.skillflow.skillshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "study_groups")
public class StudyGroup {
    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private String createdBy;
    private String createdAt;
    private String image; // Base64-encoded image
    private List<Member> members = new ArrayList<>();

    public StudyGroup() {}

    public StudyGroup(String id, String title, String description, String category, String createdBy, String createdAt, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.image = image;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }

    public static class Member {
        private String name;
        private String email;

        public Member() {}
        public Member(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}