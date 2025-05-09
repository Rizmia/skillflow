// package com.skillflow.skillshare.controller;

// import com.skillflow.skillshare.model.Course;
// import com.skillflow.skillshare.service.CourseService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/api/courses")
// public class CourseController {

//     @Autowired
//     private CourseService courseService;

//     // @PostMapping
//     // public ResponseEntity<Course> createCourse(@RequestBody Course course, @RequestHeader("User-Id") String userId) {
//     //     course.setCreatorId(userId);
//     //     Course createdCourse = courseService.createCourse(course);
//     //     return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
//     // }

//     @PostMapping
// public ResponseEntity<?> createCourse(@RequestBody Course course, @RequestHeader("User-Id") String userId) {
//     if (course.getTitle() == null || course.getTitle().trim().isEmpty()) {
//         return new ResponseEntity<>("Title is required", HttpStatus.BAD_REQUEST);
//     }
//     if (course.getDescription() == null || course.getDescription().trim().isEmpty()) {
//         return new ResponseEntity<>("Description is required", HttpStatus.BAD_REQUEST);
//     }
//     if (course.getCategory() == null || course.getCategory().trim().isEmpty()) {
//         return new ResponseEntity<>("Category is required", HttpStatus.BAD_REQUEST);
//     }
//     course.setCreatorId(userId);
//     Course createdCourse = courseService.createCourse(course);
//     return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
// }

//     @GetMapping
//     public ResponseEntity<List<Course>> getAllCourses() {
//         List<Course> courses = courseService.getAllCourses();
//         return new ResponseEntity<>(courses, HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Course> getCourseById(@PathVariable String id) {
//         Optional<Course> course = courseService.getCourseById(id);
//         return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                 .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @GetMapping("/category/{category}")
//     public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
//         List<Course> courses = courseService.getCoursesByCategory(category);
//         return new ResponseEntity<>(courses, HttpStatus.OK);
//     }

//     @GetMapping("/tag/{tag}")
//     public ResponseEntity<List<Course>> getCoursesByTag(@PathVariable String tag) {
//         List<Course> courses = courseService.getCoursesByTag(tag);
//         return new ResponseEntity<>(courses, HttpStatus.OK);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course course, @RequestHeader("User-Id") String userId) {
//         try {
//             Course updatedCourse = courseService.updateCourse(id, course, userId);
//             return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
//         } catch (RuntimeException e) {
//             return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteCourse(@PathVariable String id, @RequestHeader("User-Id") String userId) {
//         try {
//             courseService.deleteCourse(id, userId);
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         } catch (RuntimeException e) {
//             return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//         }
//     }

//     // New search endpoint
//     @GetMapping("/search")
//     public ResponseEntity<List<Course>> searchCourses(@RequestParam("query") String query) {
//         List<Course> courses = courseService.searchCourses(query);
//         return new ResponseEntity<>(courses, HttpStatus.OK);
//     }
// }


package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.Course;
import com.skillflow.skillshare.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course, @RequestHeader("User-Id") String userId) {
        course.setCreatorId(userId);
        Course createdCourse = courseService.createCourse(course);
        return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        List<Course> courses = courseService.getCoursesByCategory(category);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Course>> getCoursesByTag(@PathVariable String tag) {
        List<Course> courses = courseService.getCoursesByTag(tag);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable String id, @RequestBody Course course, @RequestHeader("User-Id") String userId) {
        try {
            Course updatedCourse = courseService.updateCourse(id, course, userId);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id, @RequestHeader("User-Id") String userId) {
        try {
            courseService.deleteCourse(id, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Updated search endpoint with pagination and field-specific search
    @GetMapping("/search")
    public ResponseEntity<Page<Course>> searchCourses(
            @RequestParam("query") String query,
            @RequestParam(value = "field", required = false) String field,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> courses = courseService.searchCourses(query, field, pageable);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}