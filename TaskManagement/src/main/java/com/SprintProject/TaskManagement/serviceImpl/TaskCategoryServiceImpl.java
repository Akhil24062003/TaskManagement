package com.SprintProject.TaskManagement.serviceImpl;

import com.SprintProject.TaskManagement.entity.TaskCategory;
import com.SprintProject.TaskManagement.repository.TaskCategoryRepository;
import com.SprintProject.TaskManagement.service.TaskCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskCategoryServiceImpl implements TaskCategoryService {

    @Autowired
    private TaskCategoryRepository taskCategoryRepository;

    @Override
    public TaskCategory addTaskCategory(TaskCategory taskCategory) {
        return taskCategoryRepository.save(taskCategory);
    }

    @Override
    public List<TaskCategory> getCategoriesByTaskId(Integer taskId) {
        return taskCategoryRepository.findByTask_TaskId(taskId);
    }

    @Override
    public List<TaskCategory> getTasksByCategoryId(Integer categoryId) {
        return taskCategoryRepository.findByCategory_CategoryId(categoryId);
    }
}