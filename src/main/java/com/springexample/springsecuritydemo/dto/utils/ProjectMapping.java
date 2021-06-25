package com.springexample.springsecuritydemo.dto.utils;

import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.model.entity.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapping {
    public ProjectDTO mapToProjectDTO(Project project){
        ProjectDTO dto = new ProjectDTO();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setLink(project.getProjectLink());
        return dto;
    }

    public Project mapToProjectEntity(ProjectDTO projectDTO){
        Project entity = new Project();
        entity.setId(projectDTO.getId());
        entity.setName(projectDTO.getName());
        entity.setDescription(projectDTO.getDescription());
        entity.setProjectLink(projectDTO.getLink());
        return  entity;
    }
}
