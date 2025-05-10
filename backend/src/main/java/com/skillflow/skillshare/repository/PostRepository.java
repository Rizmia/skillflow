package com.skillflow.skillshare.repository;

import com.skillflow.skillshare.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {
    List<Post> findByGroupId(String groupId);
}                                                                               