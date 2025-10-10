package com.SprintProject.TaskManagement.serviceImpl;

import com.SprintProject.TaskManagement.entity.User;
import com.SprintProject.TaskManagement.repository.TaskRepository;
import com.SprintProject.TaskManagement.repository.UserRepository;
import com.SprintProject.TaskManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public ResponseEntity<User> createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(null);
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUserById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<User>> getUsersByEmailDomain(String domain) {
        List<User> users = userRepository.findAll().stream()
                .filter(u -> u.getEmail().endsWith("@" + domain))
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<User>> searchUsersByName(String name) {
        List<User> users = userRepository.findByFullNameContainingIgnoreCase(name);
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> getUsersWithMostTasks() {
        List<Object[]> results = taskRepository.findUsersWithTaskCounts();
        if (!results.isEmpty()) {
            Integer userId = (Integer) results.get(0)[0];
            Optional<User> user = userRepository.findById(userId);
            return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> authenticateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @Override
    public ResponseEntity<List<User>> getUsersWithCompletedTasks() {
        List<User> users = taskRepository.findUsersWithCompletedTasks();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<User> updateUser(Integer userId, User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = userOptional.get();
        user.setFullName(updatedUser.getFullName());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<User> deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.badRequest().body(null);
        }
        userRepository.deleteById(userId);
        return ResponseEntity.ok().build();
    }
}