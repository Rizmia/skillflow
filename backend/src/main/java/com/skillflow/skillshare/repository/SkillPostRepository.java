package com.skillflow.skillshare.repository;

import com.skillflow.skillshare.model.SkillPost;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkillPostRepository extends MongoRepository<SkillPost, String> {}
