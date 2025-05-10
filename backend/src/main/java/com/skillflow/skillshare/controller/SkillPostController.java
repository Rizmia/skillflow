package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.SkillPost;
import com.skillflow.skillshare.service.SkillPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/skillposts")
@CrossOrigin(origins = "*")
public class SkillPostController {

    @Autowired
    private SkillPostService service;

    @PostMapping
    public SkillPost create(@RequestBody SkillPost post) {
        return service.create(post);
    }

    @GetMapping
    public List<SkillPost> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SkillPost getById(@PathVariable String id) {
        return service.getById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public SkillPost update(@PathVariable String id, @RequestBody SkillPost post) {
        return service.update(id, post);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return service.delete(id) ? "Deleted Successfully" : "Post Not Found";
    }

    @PutMapping("/{id}/like")
    public SkillPost likePost(@PathVariable String id) {
        return service.incrementLikes(id);
}

    @PostMapping("/{id}/comments")
    public SkillPost addComment(@PathVariable String id, @RequestBody String comment) {
        return service.addComment(id, comment);
}

}
