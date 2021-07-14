package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.model.entity.Project;
import com.springexample.springsecuritydemo.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "Projects/V1")
@RestController
@RequestMapping("/api/v1")
public class ProjectRestController {

    private ProjectService projectService;

    public ProjectRestController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation(value = "This method is used to get all projects with pagination.")
    @GetMapping("/projects")
    @PreAuthorize("hasAuthority('projects:read')")
    public ResponseEntity<Map<String, Object>> getAll(
            @RequestParam(required = false) String subject,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return projectService.getProjects(subject, page, size, pageable);
    }

//    @GetMapping("/projects/{id}")
//    @PreAuthorize("hasAuthority('projects:read')")
//    public ProjectDTO getById(@PathVariable Long id) {
//        return projectService.getProjectById(id);
//    }

    @ApiOperation(value = "This method is used to get project by name.")
    @GetMapping("/projects/{name}")
    @PreAuthorize("hasAuthority('projects:read')")
    public ProjectDTO getProjectByName(@PathVariable String name) {
        return projectService.getProjectByName(name);
    }

    @ApiOperation(value = "This method is used to adding project.")
    @PostMapping("/project")
    @PreAuthorize("hasAuthority('projects:write')")
    public Project addProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.saveProject(projectDTO);
    }

    @ApiOperation(value = "This method is used to adding list projects.")
    @PostMapping("/projects")
    @PreAuthorize("hasAuthority('projects:write')")
    public List<Project> addProjects(@RequestBody List<ProjectDTO> projectDTOList) {
        return projectService.saveProjects(projectDTOList);
    }

    @ApiOperation(value = "This method is used to update project.")
    @PatchMapping("/projects")
    @PreAuthorize("hasAuthority('projects:write')")
    public Project updateProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }

    @ApiOperation(value = "This method is used to delete project by id.")
    @DeleteMapping("/projects/{id}")
    @PreAuthorize("hasAuthority('projects:write')")
    public void deleteProjectById(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
