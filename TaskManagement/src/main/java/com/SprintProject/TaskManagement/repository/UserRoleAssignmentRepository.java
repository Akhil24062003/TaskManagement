package com.SprintProject.TaskManagement.repository;

import com.SprintProject.TaskManagement.entity.UserRoleAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleAssignmentRepository extends JpaRepository<UserRoleAssignment, Integer> {
    List<UserRoleAssignment> findByUserUserId(Integer userId);
}
