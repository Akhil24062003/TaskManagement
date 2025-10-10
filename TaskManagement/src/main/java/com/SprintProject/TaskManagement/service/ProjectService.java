package com.SprintProject.TaskManagement.service;

import com.SprintProject.TaskManagement.entity.Project;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ProjectService {

    ResponseEntity<Project> createProject(Project project);

    ResponseEntity<List<Project>> getAllProjects();

    ResponseEntity<List<Project>> getOngoingProjects();

    ResponseEntity<List<Project>> getProjectsByDateRange(LocalDate startDate, LocalDate endDate);

    ResponseEntity<List<Project>> getProjectsByUserRole(String roleName);

    ResponseEntity<List<Project>> getProjectsByStatus(String status);

    ResponseEntity<List<Project>> getProjectsWithHighPriorityTasks();

    ResponseEntity<Project> updateProject(Integer projectId, Project updatedProject);

    ResponseEntity<String> deleteProject(Integer projectId);
}