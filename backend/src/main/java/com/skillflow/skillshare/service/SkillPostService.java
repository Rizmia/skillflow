package com.skillflow.skillshare.service;

import com.skillflow.skillshare.model.SkillPost;
import com.skillflow.skillshare.repository.SkillPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillPostService {

    @Autowired
    private SkillPostRepository repository;

    public SkillPost create(SkillPost post) {
        return repository.save(post);
    }

    public List<SkillPost> getAll() {
        return repository.findAll();
    }

    public Optional<SkillPost> getById(String id) {
        return repository.findById(id);
    }

    public SkillPost update(String id, SkillPost updatedPost) {
        return repository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setDescription(updatedPost.getDescription());
            return repository.save(post);
        }).orElse(null);
    }

    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public SkillPost incrementLikes(String id) {
    return repository.findById(id).map(post -> {
        post.setLikes(post.getLikes() + 1);
        return repository.save(post);
    }).orElse(null);
}

    public SkillPost addComment(String id, String comment) {
    return repository.findById(id).map(post -> {
        post.getComments().add(comment);
        return repository.save(post);
    }).orElse(null);
}

}
