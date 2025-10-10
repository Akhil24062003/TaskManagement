package com.SprintProject.TaskManagement.serviceImpl;

import com.SprintProject.TaskManagement.entity.Project;
import com.SprintProject.TaskManagement.repository.ProjectRepository;
import com.SprintProject.TaskManagement.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseEntity<Project> createProject(Project project) {
        Project savedProject = projectRepository.save(project);
        return ResponseEntity.ok(savedProject);
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Project>> getOngoingProjects() {
        LocalDate today = LocalDate.now();
        List<Project> ongoingProjects = projectRepository.findByEndDateAfter(today);
        return ResponseEntity.ok(ongoingProjects);
    }

    @Override
    public ResponseEntity<List<Project>> getProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Project> projects = projectRepository.findByStartDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate);
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<List<Project>> getProjectsByUserRole(String roleName) {
        try {
            Integer userId = Integer.parseInt(roleName); // Replace with actual logic if needed
            List<Project> projects = projectRepository.findByUser_UserId(userId);
            return ResponseEntity.ok(projects);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Project>> getProjectsByStatus(String status) {
        List<Project> projects = projectRepository.findByStatus(status);
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<List<Project>> getProjectsWithHighPriorityTasks() {
        List<Project> projects = projectRepository.findByTasks_Priority("High");
        return ResponseEntity.ok(projects);
    }

    @Override
    public ResponseEntity<Project> updateProject(Integer projectId, Project updatedProject) {
        Optional<Project> existingProjectOpt = projectRepository.findById(projectId);
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            existingProject.setName(updatedProject.getName());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            existingProject.setStatus(updatedProject.getStatus());
            existingProject.setUser(updatedProject.getUser());
            existingProject.setTasks(updatedProject.getTasks());
            projectRepository.save(existingProject);
            return ResponseEntity.ok(existingProject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<String> deleteProject(Integer projectId) {
        if (projectRepository.existsById(projectId)) {
            projectRepository.deleteById(projectId);
            return ResponseEntity.ok("Project deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}