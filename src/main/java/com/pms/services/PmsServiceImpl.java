package com.pms.services;

import com.pms.models.ProjectBean;
import com.pms.repositories.PmsRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PmsServiceImpl implements PmsService {

    private final PmsRepo pmsRepo;

    public PmsServiceImpl(PmsRepo pmsRepo) {
        this.pmsRepo = pmsRepo;
    }
  
    @Override
    public List<ProjectBean> getAllProjects() {
        return pmsRepo.findAll();
    }
    
    @Override
    public Optional<ProjectBean> getProjectById(Long id) {
        return pmsRepo.findById(id);
    }

    @Override
    public ProjectBean createProject(ProjectBean project) {
        return pmsRepo.save(project);
    }

    @Override
    public ProjectBean updateProject(Long id, ProjectBean project) {
        if (pmsRepo.existsById(id)) {
            project.setId(id); // Ensure the project id is set
            return pmsRepo.save(project);
        } else {
            throw new IllegalArgumentException("Project with ID " + id + " not found");
        }
    }

    @Override
    public void deleteProject(Long id) {
        pmsRepo.deleteById(id);
    }

    @Override
    public List<ProjectBean> filterProjectsByDateRange(LocalDate startDate, LocalDate endDate) {
        return pmsRepo.findByStartDateBetween(startDate, endDate);
    }

    @Override
    public List<ProjectBean> sortProjectsByStartDate(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return pmsRepo.findAllByOrderByStartDateAsc();
        } else {
            return pmsRepo.findAllByOrderByStartDateDesc();
        }
    }

    @Override
    public List<ProjectBean> sortProjectsByEndDate(String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return pmsRepo.findAllByOrderByEndDateAsc();
        } else {
            return pmsRepo.findAllByOrderByEndDateDesc();
        }
    }

    @Override
    public void deleteAllProjects() {
        pmsRepo.deleteAll();
    }
}
