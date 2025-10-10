package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.TaskCategory;
import com.SprintProject.TaskManagement.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taskcategories")
public class TaskCategoryController {

    @Autowired
    private TaskCategoryService taskCategoryService;

    @PostMapping("/post")
    public ResponseEntity<?> addTaskCategory(@RequestBody TaskCategory taskCategory) {
        try {
            TaskCategory saved = taskCategoryService.addTaskCategory(taskCategory);
            return ResponseEntity.ok().body(
                java.util.Map.of("code", "POSTSUCCESS", "message", "Task-category added successfully", "data", saved)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of("code", "ADDFAILS", "message", "Task-Category already exists or invalid")
            );
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<?> getCategoriesByTask(@PathVariable Integer taskId) {
        List<TaskCategory> list = taskCategoryService.getCategoriesByTaskId(taskId);
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of("code", "GETFAILS", "message", "No category found for a particular task")
            );
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getTasksByCategory(@PathVariable Integer categoryId) {
        List<TaskCategory> list = taskCategoryService.getTasksByCategoryId(categoryId);
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(
                java.util.Map.of("code", "GETFAILS", "message", "No task found for a particular category")
            );
        }
        return ResponseEntity.ok(list);
    }
}