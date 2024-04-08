package com.pms.controllers;

import com.pms.models.ProjectBean;
import com.pms.services.PmsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class PmsController {

    private final PmsService pmsService;

    public PmsController(PmsService pmsService) {
        this.pmsService = pmsService;
    }

    @GetMapping
    public ResponseEntity<List<ProjectBean>> getAllProjects() {
        List<ProjectBean> projects = pmsService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectBean> getProjectById(@PathVariable Long id) {
        Optional<ProjectBean> project = pmsService.getProjectById(id);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProjectBean> createProject(@RequestBody ProjectBean project) {
        ProjectBean createdProject = pmsService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectBean> updateProject(@PathVariable Long id, @RequestBody ProjectBean project) {
        ProjectBean updatedProject = pmsService.updateProject(id, project);
        return ResponseEntity.ok(updatedProject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        pmsService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProjectBean>> filterProjectsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate) {
        List<ProjectBean> projects = pmsService.filterProjectsByDateRange(startDate, endDate);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/sort/startDate")
    public ResponseEntity<List<ProjectBean>> sortProjectsByStartDate(@RequestParam(defaultValue = "asc") String order) {
        List<ProjectBean> projects = pmsService.sortProjectsByStartDate(order);
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/sort/endDate")
    public ResponseEntity<List<ProjectBean>> sortProjectsByEndDate(@RequestParam(defaultValue = "asc") String order) {
        List<ProjectBean> projects = pmsService.sortProjectsByEndDate(order);
        return ResponseEntity.ok(projects);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllProjects() {
        pmsService.deleteAllProjects();
        return ResponseEntity.noContent().build();
    }
}
