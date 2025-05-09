package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.StudyGroup;
import com.skillflow.skillshare.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
public class GroupController {

    @Autowired
    private StudyGroupService service;

    private final String currentUser = "linarasenarathna2001@gmail.com";

    @GetMapping
    public ResponseEntity<List<StudyGroup>> getAllGroups() {
        try { return ResponseEntity.ok(service.getAllGroups()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyGroup> getGroupById(@PathVariable String id) {
        try { return service.getGroupById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); }
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createGroup(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            if (title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty() || category == null || category.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Title, description, and category are required.");
            }
            StudyGroup group = new StudyGroup();
            group.setTitle(title);
            group.setDescription(description);
            group.setCategory(category);
            group.setCreatedBy(currentUser);
            group.setCreatedAt(new java.util.Date().toString());
            if (image != null && !image.isEmpty()) group.setImage(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
            return ResponseEntity.ok(service.createGroup(group));
        } catch (IOException e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating group: " + e.getMessage()); }
    }

    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateGroup(
            @PathVariable String id,
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            if (title == null || title.trim().isEmpty() || description == null || description.trim().isEmpty() || category == null || category.trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Title, description, and category are required.");
            }
            StudyGroup group = new StudyGroup();
            group.setId(id);
            group.setTitle(title);
            group.setDescription(description);
            group.setCategory(category);
            group.setCreatedBy(currentUser);
            group.setCreatedAt(new java.util.Date().toString());
            if (image != null && !image.isEmpty()) group.setImage(java.util.Base64.getEncoder().encodeToString(image.getBytes()));
            return ResponseEntity.ok(service.updateGroup(id, group));
        } catch (IOException e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing image: " + e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating group: " + e.getMessage()); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGroup(@PathVariable String id) {
        try { service.deleteGroup(id); return ResponseEntity.ok("Group deleted successfully"); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting group: " + e.getMessage()); }
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<?> joinGroup(@PathVariable String id, @RequestBody StudyGroup.Member member) {
        try {
            if (member.getName() == null || member.getName().trim().isEmpty() || member.getEmail() == null || member.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Name and email are required.");
            }
            return ResponseEntity.ok(service.addMemberToGroup(id, member));
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error joining group: " + e.getMessage()); }
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<?> leaveGroup(@PathVariable String id, @RequestBody StudyGroup.Member member) {
        try {
            if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Email is required.");
            }
            StudyGroup updatedGroup = service.removeMemberFromGroup(id, member.getEmail());
            return ResponseEntity.ok(updatedGroup);
        } catch (IllegalArgumentException e) { return ResponseEntity.badRequest().body(e.getMessage()); } catch (Exception e) { return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error leaving group: " + e.getMessage()); }
    }
}