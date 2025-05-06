// package com.skillflow.Course.SkillManagement.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
// import com.backend.backend.course.model.course;
// import com.skillflow.Course.SkillManagement.repository.CourseRepository;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/courses")
// public class coursecontroller {

//     @Autowired
//     private CourseRepository courseRepository;

//     // CREATE: Add a new course
//     @PostMapping
//     public ResponseEntity<Course> createCourse(@RequestBody Course course) {
//         Course savedCourse = courseRepository.save(course);
//         return new ResponseEntity<>(savedCourse, HttpStatus.CREATED); // 201 Created
//     }

//     // READ: Get all courses
//     @GetMapping
//     public ResponseEntity<List<Course>> getAllCourses() {
//         List<Course> courses = courseRepository.findAll();
//         return new ResponseEntity<>(courses, HttpStatus.OK); // 200 OK
//     }

//     // READ: Get a course by ID
//     @GetMapping("/{id}")
//     public ResponseEntity<Course> getCourseById(@PathVariable String id) {
//         Optional<Course> course = courseRepository.findById(id);
//         return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
//     }

//     // UPDATE: Modify a course
//     @PutMapping("/{id}")
//     public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course updatedCourse) {
//         Optional<Course> existingCourse = courseRepository.findById(id);
//         if (existingCourse.isPresent()) {
//             updatedCourse.setId(id); // Ensure ID remains the same
//             Course savedCourse = courseRepository.save(updatedCourse);
//             return new ResponseEntity<>(savedCourse, HttpStatus.OK); // 200 OK
//         }
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
//     }

//     // DELETE: Remove a course
//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteCourse(@PathVariable String id) {
//         if (courseRepository.existsById(id)) {
//             courseRepository.deleteById(id);
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
//         }
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
//     }

//     // READ: Search courses by category
//     @GetMapping("/category/{category}")
//     public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
//         List<Course> courses = courseRepository.findByCategory(category);
//         return new ResponseEntity<>(courses, HttpStatus.OK); // 200 OK
//     }
// }