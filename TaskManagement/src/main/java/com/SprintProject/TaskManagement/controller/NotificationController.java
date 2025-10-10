package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.Notification;
import com.SprintProject.TaskManagement.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/post")
    public ResponseEntity<?> addNotification(@RequestBody Notification notification) {
        try {
            Notification saved = notificationService.addNotification(notification);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "Notification successfully", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", "Notification already exists"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        if (notifications.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "Notification list is empty"));
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(notificationService.getNotificationById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNotification(@PathVariable Integer id, @RequestBody Notification notification) {
        try {
            Notification updated = notificationService.updateNotification(id, notification);
            return ResponseEntity.ok(Map.of("code", "UPDATESUCCESS", "message", "Notification updated successfully", "data", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "UPDTFAILS", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Integer id) {
        try {
            notificationService.deleteNotification(id);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "Notification deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }
}