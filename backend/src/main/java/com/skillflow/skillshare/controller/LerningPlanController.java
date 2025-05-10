package com.skillflow.skillshare.controller;

import com.skillflow.skillshare.model.LearningPlan;
import com.skillflow.skillshare.service.LearningPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class LerningPlanController {

    @Autowired
    private LearningPlanService service;

    /**
     * GET /api/courses
     * Optional filters: subject, priorityLevel, before, after, includeArchived
     * Pagination: page (0-index), size, sort
     */
    @GetMapping
    public Page<LearningPlan> getAll(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String priorityLevel,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate targetDate,
            @RequestParam(defaultValue = "false") boolean includeArchived,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title,asc") String[] sort
    ) {
        Sort.Direction dir = Sort.Direction.fromString(sort[1]);
        Sort s = Sort.by(dir, sort[0]);
        Pageable pageable = PageRequest.of(page, size, s);

        return service.findAll(title, priorityLevel, targetDate, includeArchived, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningPlan> getById(@PathVariable String id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LearningPlan create(@RequestBody LearningPlan course) {
        return service.create(course);
    }

    @PutMapping("/{id}")
    public LearningPlan update(@PathVariable String id, @RequestBody LearningPlan course) {
        return service.update(id, course);
    }

    /** Permanent delete */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String id) {
        service.delete(id);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "Course deleted permanently");
        return ResponseEntity.ok(resp);
    }

    /** Archive instead of delete */
    @PatchMapping("/{id}/archive")
    public LearningPlan archive(@PathVariable String id) {
        return service.archive(id);
    }

    /** Mark as completed/uncompleted */
    @PatchMapping("/{id}/complete")
    public LearningPlan markCompleted(
            @PathVariable String id,
            @RequestParam(defaultValue = "true") boolean completed
    ) {
        return service.markCompleted(id, completed);
    }
}
