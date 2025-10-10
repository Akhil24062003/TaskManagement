package com.SprintProject.TaskManagement.repository;

import com.SprintProject.TaskManagement.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByEndDateAfter(LocalDate currentDate);

    List<Project> findByStartDateGreaterThanEqualAndEndDateLessThanEqual(LocalDate startDate, LocalDate endDate);

    List<Project> findByUser_UserId(Integer userId);

    List<Project> findByStatus(String status); // ✅ Now valid

    List<Project> findByTasks_Priority(String priority); // ✅ Assuming Task has a 'priority' field
    
    
}
