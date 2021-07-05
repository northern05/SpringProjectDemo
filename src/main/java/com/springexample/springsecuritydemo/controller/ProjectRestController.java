package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.model.entity.Project;
import com.springexample.springsecuritydemo.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectRestController {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    public List<ProjectDTO> getAll() {
        return projectService.getProjects();
    }

//    @GetMapping("/projects/{id}")
//    @PreAuthorize("hasAuthority('projects:read')")
//    public ProjectDTO getById(@PathVariable Long id) {
//        return projectService.getProjectById(id);
//    }

    @GetMapping("/projects/{name}")
    @PreAuthorize("hasAuthority('projects:read')")
    public ProjectDTO getProjectByName(@PathVariable String name) {
        return projectService.getProjectByName(name);
    }

    @PostMapping("/project")
    @PreAuthorize("hasAuthority('projects:write')")
    public Project addProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @PostMapping("/projects")
    @PreAuthorize("hasAuthority('projects:write')")
    public List<Project> addProjects(@RequestBody List<ProjectDTO> projectDTOList) {
        return projectService.saveProjects(projectDTOList);
    }

    @PatchMapping("/projects")
    @PreAuthorize("hasAuthority('projects:write')")
    public Project updateProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }

    @DeleteMapping("/projects/{id}")
    @PreAuthorize("hasAuthority('projects:write')")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
