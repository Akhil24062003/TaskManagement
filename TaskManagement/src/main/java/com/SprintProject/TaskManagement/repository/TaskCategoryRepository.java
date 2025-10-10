package com.SprintProject.TaskManagement.repository;

import com.SprintProject.TaskManagement.entity.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {
    List<TaskCategory> findByTask_TaskId(Integer taskId);
    List<TaskCategory> findByCategory_CategoryId(Integer categoryId);
}