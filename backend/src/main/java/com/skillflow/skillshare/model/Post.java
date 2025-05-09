package com.skillflow.skillshare.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String groupId;
    private String createdBy;
    private String caption;
    private String description;
    private String media; // Base64-encoded image or video
    private Date createdAt;
    private List<Like> likes = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();

    public Post() {}

    public Post(String id, String groupId, String createdBy, String caption, String description, String media, Date createdAt) {
        this.id = id;
        this.groupId = groupId;
        this.createdBy = createdBy;
        this.caption = caption;
        this.description = description;
        this.media = media;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getGroupId() { return groupId; }
    public void setGroupId(String groupId) { this.groupId = groupId; }
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getMedia() { return media; }
    public void setMedia(String media) { this.media = media; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public List<Like> getLikes() { return likes; }
    public void setLikes(List<Like> likes) { this.likes = likes; }
    public List<Comment> getComments() { return comments; }
    public void setComments(List<Comment> comments) { this.comments = comments; }

    public static class Like {
        private String userEmail;

        public Like() {}
        public Like(String userEmail) { this.userEmail = userEmail; }
        public String getUserEmail() { return userEmail; }
        public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    }

    public static class Comment {
        private String userEmail;
        private String text;
        private Date createdAt;

        public Comment() {}
        public Comment(String userEmail, String text, Date createdAt) {
            this.userEmail = userEmail;
            this.text = text;
            this.createdAt = createdAt;
        }
        public String getUserEmail() { return userEmail; }
        public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
        public Date getCreatedAt() { return createdAt; }
        public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    }
}