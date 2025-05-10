package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.Post;
import com.skillflow.skillshare.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class PostController {

    @Autowired
    private PostService service;

    private final String currentUser = "linarasenarathna2001@gmail.com";

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Post>> getPostsByGroupId(@PathVariable String groupId) {
        try { return ResponseEntity.ok(service.getPostsByGroupId(groupId)); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createPost(
            @RequestParam("groupId") String groupId,
            @RequestParam("caption") String caption,
            @RequestParam("description") String description,
            @RequestParam(value = "media", required = false) MultipartFile media) {
        try {
            if (groupId == null || groupId.trim().isEmpty() || caption == null || caption.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Group ID, caption, and description are required.");
            }
            Post post = new Post();
            post.setGroupId(groupId);
            post.setCreatedBy(currentUser);
            post.setCaption(caption);
            post.setDescription(description);
            post.setCreatedAt(new java.util.Date());
            if (media != null && !media.isEmpty()) post.setMedia(java.util.Base64.getEncoder().encodeToString(media.getBytes()));
            return ResponseEntity.ok(service.createPost(post));
        } catch (IOException e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing media: " + e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating post: " + e.getMessage()); }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updatePost(
            @PathVariable String id,
            @RequestParam("caption") String caption,
            @RequestParam("description") String description,
            @RequestParam(value = "media", required = false) MultipartFile media) {
        try {
            if (caption == null || caption.trim().isEmpty() || description == null || description.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Caption and description are required.");
            }
            Post post = new Post();
            post.setCaption(caption);
            post.setDescription(description);
            if (media != null && !media.isEmpty()) post.setMedia(java.util.Base64.getEncoder().encodeToString(media.getBytes()));
            return ResponseEntity.ok(service.updatePost(id, post));
        } catch (IOException e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing media: " + e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating post: " + e.getMessage()); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable String id) {
        try { service.deletePost(id); return ResponseEntity.ok("Post deleted successfully"); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting post: " + e.getMessage()); }
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> addLike(@PathVariable String id, @RequestBody Post.Like like) {
        try { return ResponseEntity.ok(service.addLike(id, like)); } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding like: " + e.getMessage()); }
    }

    @PostMapping("/{id}/comment")
    public ResponseEntity<?> addComment(@PathVariable String id, @RequestBody Post.Comment comment) {
        try { return ResponseEntity.ok(service.addComment(id, comment)); } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding comment: " + e.getMessage()); }
    }
}
