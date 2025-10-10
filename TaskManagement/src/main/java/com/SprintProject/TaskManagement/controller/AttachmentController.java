package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.Attachment;
import com.SprintProject.TaskManagement.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @PostMapping("/post")
    public ResponseEntity<?> addAttachment(@RequestBody Attachment attachment) {
        try {
            Attachment saved = attachmentService.addAttachment(attachment);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "Attachment added successfully", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", "Attachment already exists"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttachments() {
        List<Attachment> attachments = attachmentService.getAllAttachments();
        if (attachments.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "Attachment list is empty"));
        }
        return ResponseEntity.ok(attachments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttachmentById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(attachmentService.getAttachmentById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAttachment(@PathVariable Integer id, @RequestBody Attachment attachment) {
        try {
            Attachment updated = attachmentService.updateAttachment(id, attachment);
            return ResponseEntity.ok(Map.of("code", "UPDATESUCCESS", "message", "Attachment updated successfully", "data", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "UPDTFAILS", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAttachment(@PathVariable Integer id) {
        try {
            attachmentService.deleteAttachment(id);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "Attachment deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }
}