package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.UserRole;

import java.util.List;

public interface UserRoleService {
    UserRole addUserRole(UserRole userRole);
    List<UserRole> getAllRoles();
    UserRole getRoleById(Integer id);
    UserRole updateRole(Integer id, UserRole userRole);
    void deleteRole(Integer id);
}