package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.dto.utils.ProjectMapping;
import com.springexample.springsecuritydemo.model.entity.Department;
import com.springexample.springsecuritydemo.model.entity.Project;
import com.springexample.springsecuritydemo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private final ProjectRepository repository;
    private final ProjectMapping projectMapping;

    public ProjectService(ProjectRepository repository, ProjectMapping projectMapping) {
        this.repository = repository;
        this.projectMapping = projectMapping;
    }

    public Project saveProject (Project project){
        return repository.save(project);
    }

    public List<Project> saveProjects(List<Project> project) {
        return repository.saveAll(project);
    }

    public List<ProjectDTO> getProjects(){
        return repository.findAll().stream().map(projectMapping::mapToProjectDTO).collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id){
        return projectMapping.mapToProjectDTO(repository.findById(id).orElse(null));
    }
    public ProjectDTO getProjectByName(String name){
        return projectMapping.mapToProjectDTO(repository.findProjectByName(name));
    }

    public void deleteProject(Long id){
        repository.deleteById(id);
    }

    public Project updateProject (Project project) {
        Project existingProject = repository.findById(project.getId()).orElse(null);
        existingProject.setName(project.getName());
        existingProject.setDescription(project.getDescription());
        existingProject.setProjectLink(project.getProjectLink());
        existingProject.setDeveloperSet(project.getDeveloperSet());
        return repository.save(existingProject);
    }
}
