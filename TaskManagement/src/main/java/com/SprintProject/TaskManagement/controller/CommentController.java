package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.Comment;
import com.SprintProject.TaskManagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        try {
            Comment saved = commentService.addComment(comment);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "Comments added successfully", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", "Comments already exist"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        if (comments.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "Comment list is empty"));
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(commentService.getCommentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            Comment updated = commentService.updateComment(id, comment);
            return ResponseEntity.ok(Map.of("code", "UPDATESUCCESS", "message", "Comment updated successfully", "data", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "UPDTFAILS", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer id) {
        try {
            commentService.deleteComment(id);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "Comment deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }
}
