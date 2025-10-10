package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    Category addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Integer id);

    Category updateCategory(Integer id, Category category);

    void deleteCategory(Integer id);

    Map<String, Long> getCategoryTaskCounts();
}