package com.SprintProject.TaskManagement.service;
 
import com.SprintProject.TaskManagement.entity.Task;
import org.springframework.http.ResponseEntity;
 
import java.util.List;
 
public interface TaskService {
 
    ResponseEntity<Task> createTask(Task task);
 
    ResponseEntity<List<Task>> getOverdueTasks();
 
    ResponseEntity<List<Task>> getTasksByPriorityAndStatus(String priority, String status);
 
    ResponseEntity<List<Task>> getDueSoonTasks();
 
    ResponseEntity<List<Task>> getTasksByUserAndStatus(Integer userId, String status);
 
    ResponseEntity<List<Task>> getTasksByCategory(Integer categoryId);
 
    ResponseEntity<Task> updateTask(Integer taskId, Task task);
 
    ResponseEntity<Task> getTaskById(Integer taskId);
   
    ResponseEntity<Task> deleteTask(Integer taskId);
 
    //void deleteTask(Integer taskId); // ✅ Newly added method
}