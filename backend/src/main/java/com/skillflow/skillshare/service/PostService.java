package com.skillflow.skillshare.service;

import com.skillflow.skillshare.model.Post;
import com.skillflow.skillshare.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public List<Post> getPostsByGroupId(String groupId) { return repo.findByGroupId(groupId); }
    public Post createPost(Post post) { return repo.save(post); }
    public Optional<Post> getPostById(String id) { return repo.findById(id); }
    public Post updatePost(String id, Post updatedPost) {
        updatedPost.setId(id);
        return repo.save(updatedPost);
    }
    public void deletePost(String id) { repo.deleteById(id); }

    public Post addLike(String postId, Post.Like like) {
        Optional<Post> postOpt = repo.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            boolean likeExists = post.getLikes().stream()
                    .anyMatch(l -> l.getUserEmail().equalsIgnoreCase(like.getUserEmail()));
            if (!likeExists) {
                post.getLikes().add(like);
                return repo.save(post);
            } else {
                throw new IllegalArgumentException("User already liked this post.");
            }
        } else {
            throw new IllegalArgumentException("Post not found.");
        }
    }

    public Post addComment(String postId, Post.Comment comment) {
        Optional<Post> postOpt = repo.findById(postId);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            post.getComments().add(comment);
            return repo.save(post);
        } else {
            throw new IllegalArgumentException("Post not found.");
        }
    }
}                                                                                           