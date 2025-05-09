// package com.skillflow.skillshare.repository;

// import com.skillflow.skillshare.model.Course;
// import org.springframework.data.mongodb.repository.MongoRepository;

// import java.util.List;

// public interface CourseRepository extends MongoRepository<Course, String> {
//     List<Course> findByCategory(String category);
//     List<Course> findByTagsContaining(String tag);
//     List<Course> findByCreatorId(String creatorId);
// }

package com.skillflow.skillshare.repository;

import com.skillflow.skillshare.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByCategory(String category);
    List<Course> findByTagsContaining(String tag);
    List<Course> findByCreatorId(String creatorId);

    // Search by title
    @Query("{ title: { $regex: ?0, $options: 'i' } }")
    Page<Course> findByTitleContaining(String query, Pageable pageable);

    // Search by category
    @Query("{ category: { $regex: ?0, $options: 'i' } }")
    Page<Course> findByCategoryContaining(String query, Pageable pageable);

    // Search by tags
    @Query("{ tags: { $regex: ?0, $options: 'i' } }")
    Page<Course> findByTagsContainingPaged(String query, Pageable pageable);

    // General search across all fields
    @Query("{ $or: [ " +
           "{ title: { $regex: ?0, $options: 'i' } }, " +
           "{ category: { $regex: ?0, $options: 'i' } }, " +
           "{ tags: { $regex: ?0, $options: 'i' } } " +
           "] }")
    Page<Course> searchCourses(String query, Pageable pageable);
}