package com.SprintProject.TaskManagement.controller;

import com.SprintProject.TaskManagement.entity.UserRole;
import com.SprintProject.TaskManagement.entity.UserRoleAssignment;
import com.SprintProject.TaskManagement.service.UserRoleService;
import com.SprintProject.TaskManagement.service.UserRoleAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/userroles")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserRoleAssignmentService assignmentService;

    @PostMapping("/post")
    public ResponseEntity<?> addUserRole(@RequestBody UserRole userRole) {
        try {
            UserRole saved = userRoleService.addUserRole(userRole);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "UserRoles added successfully", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", "UserRoles already exist"));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllRoles() {
        List<UserRole> roles = userRoleService.getAllRoles();
        if (roles.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "UserRoles list is empty"));
        }
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(userRoleService.getRoleById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody UserRole userRole) {
        try {
            UserRole updated = userRoleService.updateRole(id, userRole);
            return ResponseEntity.ok(Map.of("code", "UPDATESUCCESS", "message", "UserRoles updated successfully", "data", updated));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "UPDTFAILS", "message", e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        try {
            userRoleService.deleteRole(id);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "UserRoles deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }

    @PostMapping("/assign/assign")
    public ResponseEntity<?> assignRole(@RequestBody UserRoleAssignment assignment) {
        try {
            UserRoleAssignment saved = assignmentService.assignRole(assignment);
            return ResponseEntity.ok(Map.of("code", "POSTSUCCESS", "message", "Userrole added successfully", "data", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("code", "ADDFAILS", "message", "UserRole already exists"));
        }
    }

    @GetMapping("/assignments")
    public ResponseEntity<?> getAllAssignments() {
        List<UserRoleAssignment> list = assignmentService.getAllAssignments();
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETALLFAILS", "message", "UserRole list is empty"));
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getRolesByUser(@PathVariable Integer userId) {
        List<UserRoleAssignment> list = assignmentService.getRolesByUserId(userId);
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("code", "GETFAILS", "message", "UserRole doesn't exist"));
        }
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/revoke/{userRoleId}/{userId}")
    public ResponseEntity<?> revokeRole(@PathVariable Integer userRoleId, @PathVariable Integer userId) {
        try {
            assignmentService.revokeRole(userRoleId, userId);
            return ResponseEntity.ok(Map.of("code", "DELETESUCCESS", "message", "UserRole deleted successfully"));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("code", "DLTFAILS", "message", e.getMessage()));
        }
    }
}