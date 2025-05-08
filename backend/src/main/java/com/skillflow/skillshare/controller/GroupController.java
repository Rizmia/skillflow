package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.StudyGroup;
import com.skillflow.skillshare.service.StudyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@CrossOrigin(origins = "*")
public class GroupController {

    @Autowired
    private StudyGroupService service;

    // Temporarily hardcoded user data for testing
    private final String currentUser = "linarasenarathna2001@gmail.com";
    private final boolean isAdmin = false;

    @GetMapping
    public List<StudyGroup> getAllGroups() {
        return service.getAllGroups();
    }

    @PostMapping
    public StudyGroup createGroup(@RequestBody StudyGroup group) {
        group.setCreatedBy(currentUser);
        return service.createGroup(group);
    }

    @PutMapping("/{id}")
    public String updateGroup(@PathVariable String id, @RequestBody StudyGroup group) {
        if (service.canEditOrDelete(id, currentUser, isAdmin)) {
            group.setId(id);
            return "Updated: " + service.updateGroup(id, group).getId();
        } else {
            return "Unauthorized to update this group";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteGroup(@PathVariable String id) {
        if (service.canEditOrDelete(id, currentUser, isAdmin)) {
            service.deleteGroup(id);
            return "Group deleted successfully";
        } else {
            return "Unauthorized to delete this group";
        }
    }
}
