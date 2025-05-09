package com.skillflow.skillshare.service;

import com.skillflow.skillshare.model.Course;
import com.skillflow.skillshare.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> getCoursesByTag(String tag) {
        return courseRepository.findByTagsContaining(tag);
    }

    public Course updateCourse(String id, Course updatedCourse, String userId) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent() && existingCourse.get().getCreatorId().equals(userId)) {
            updatedCourse.setId(id);
            updatedCourse.setCreatorId(existingCourse.get().getCreatorId());
            return courseRepository.save(updatedCourse);
        }
        throw new RuntimeException("Course not found or user not authorized");
    }

    public void deleteCourse(String id, String userId) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent() && course.get().getCreatorId().equals(userId)) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course not found or user not authorized");
        }
    }

    // Updated search method with pagination and field-specific search
    public Page<Course> searchCourses(String query, String field, Pageable pageable) {
        if (query == null || query.trim().isEmpty()) {
            return courseRepository.findAll(pageable);
        }
        switch (field == null ? "all" : field.toLowerCase()) {
            case "title":
                return courseRepository.findByTitleContaining(query, pageable);
            case "category":
                return courseRepository.findByCategoryContaining(query, pageable);
            case "tags":
                return courseRepository.findByTagsContainingPaged(query, pageable);
            default:
                return courseRepository.searchCourses(query, pageable);
        }
    }
}