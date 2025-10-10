package com.SprintProject.TaskManagement.repository;

import com.SprintProject.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
    List<User> findByFullNameContainingIgnoreCase(String name);
}