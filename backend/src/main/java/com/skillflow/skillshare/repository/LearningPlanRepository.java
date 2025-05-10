package com.skillflow.skillshare.repository;

import com.skillflow.skillshare.model.LearningPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LearningPlanRepository extends MongoRepository<LearningPlan, String> { }
