package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.TaskCategory;

import java.util.List;

public interface TaskCategoryService {
    TaskCategory addTaskCategory(TaskCategory taskCategory);
    List<TaskCategory> getCategoriesByTaskId(Integer taskId);
    List<TaskCategory> getTasksByCategoryId(Integer categoryId);
}