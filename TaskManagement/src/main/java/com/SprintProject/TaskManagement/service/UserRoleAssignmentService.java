package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.UserRoleAssignment;

import java.util.List;

public interface UserRoleAssignmentService {
    UserRoleAssignment assignRole(UserRoleAssignment assignment);
    List<UserRoleAssignment> getAllAssignments();
    List<UserRoleAssignment> getRolesByUserId(Integer userId);
    void revokeRole(Integer userRoleId, Integer userId);
}