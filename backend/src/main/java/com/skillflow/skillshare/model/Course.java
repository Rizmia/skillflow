// package com.skillflow.skillshare.model;

// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;

// import java.util.List;

// @Document(collection = "courses")
// public class Course {
//     @Id
//     private String id;
//     private String title;
//     private String description;
//     private String category;
//     private String level; // Beginner, Intermediate, Advanced
//     private List<String> materials; // URLs or IDs for videos, PDFs, etc.
//     private List<String> tags;
//     private String creatorId; // ID of the user who created the course

//     // Constructors
//     public Course() {}

//     public Course(String title, String description, String category, String level, List<String> materials, List<String> tags, String creatorId) {
//         this.title = title;
//         this.description = description;
//         this.category = category;
//         this.level = level;
//         this.materials = materials;
//         this.tags = tags;
//         this.creatorId = creatorId;
//     }

//     // Getters and Setters
//     public String getId() { return id; }
//     public void setId(String id) { this.id = id; }
//     public String getTitle() { return title; }
//     public void setTitle(String title) { this.title = title; }
//     public String getDescription() { return description; }
//     public void setDescription(String description) { this.description = description; }
//     public String getCategory() { return category; }
//     public void setCategory(String category) { this.category = category; }
//     public String getLevel() { return level; }
//     public void setLevel(String level) { this.level = level; }
//     public List<String> getMaterials() { return materials; }
//     public void setMaterials(List<String> materials) { this.materials = materials; }
//     public List<String> getTags() { return tags; }
//     public void setTags(List<String> tags) { this.tags = tags; }
//     public String getCreatorId() { return creatorId; }
//     public void setCreatorId(String creatorId) { this.creatorId = creatorId; }
// }


package com.skillflow.skillshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Document(collection = "courses")
public class Course {
    @Id
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotNull(message = "Level is required")
    private String level;

    private List<String> materials;
    private List<String> tags;
    private String creatorId;

    public Course() {}

    public Course(String title, String description, String category, String level, List<String> materials, List<String> tags, String creatorId) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.level = level;
        this.materials = materials;
        this.tags = tags;
        this.creatorId = creatorId;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public List<String> getMaterials() { return materials; }
    public void setMaterials(List<String> materials) { this.materials = materials; }
    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }
    public String getCreatorId() { return creatorId; }
    public void setCreatorId(String creatorId) { this.creatorId = creatorId; }
}