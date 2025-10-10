package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.Category;
import com.SprintProject.TaskManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/post")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        try {
            Category saved = categoryService.addCategory(category);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "Category added successfully", "data", saved));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "Category list is empty"));
        }
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {
        try {
            Category updated = categoryService.updateCategory(id, category);
            return ResponseEntity.ok(Map.of("code", "UPDATESUCCESS", "message", "Category updated successfully", "data", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "UPDTFAILS", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
        try {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "Category deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }

    @GetMapping("/task-count")
    public ResponseEntity<?> getCategoryTaskCounts() {
        try {
            Map<String, Long> counts = categoryService.getCategoryTaskCounts();
            return ResponseEntity.ok(counts);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }
}