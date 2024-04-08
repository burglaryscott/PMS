package com.pms.services;

import com.pms.models.ProjectBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PmsService {
    List<ProjectBean> getAllProjects();

    Optional<ProjectBean> getProjectById(Long id);

    ProjectBean createProject(ProjectBean project);

    ProjectBean updateProject(Long id, ProjectBean project);

    void deleteProject(Long id);

    List<ProjectBean> filterProjectsByDateRange(LocalDate startDate, LocalDate endDate);

    List<ProjectBean> sortProjectsByStartDate(String order);

    List<ProjectBean> sortProjectsByEndDate(String order);

    void deleteAllProjects();
}
