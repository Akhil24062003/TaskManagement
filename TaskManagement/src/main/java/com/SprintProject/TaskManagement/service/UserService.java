package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseEntity<User> createUser(User user);
    ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<User> getUserById(Integer userId);
    ResponseEntity<List<User>> getUsersByEmailDomain(String domain);
    ResponseEntity<List<User>> searchUsersByName(String name);
    ResponseEntity<User> getUsersWithMostTasks();
    ResponseEntity<User> authenticateUser(String username, String password);
    ResponseEntity<List<User>> getUsersWithCompletedTasks();
    ResponseEntity<User> updateUser(Integer userId, User user);
    ResponseEntity<User> deleteUser(Integer userId);
}