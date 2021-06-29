package com.springexample.springsecuritydemo.dto.utils;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.model.entity.Developer;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DeveloperMapping {

    private  ProjectMapping projectMapping;

    public DeveloperMapping(ProjectMapping projectMapping) {
        this.projectMapping = projectMapping;
    }

    public DeveloperDTO mapToDeveloperDTO (Developer developer){
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(developer.getId());
        dto.setEmail(developer.getEmail());
        dto.setFirstName(developer.getFirstName());
        dto.setLastName(developer.getLastName());
        dto.setProjectDTOList(developer.getProjectList()
                .stream().map(projectMapping::mapToProjectDTO)
                .collect(Collectors.toSet()));
        return  dto;
    }

    public Developer mapToDeveloperEntity(DeveloperDTO developerDTO){
        Developer entity = new Developer();
        entity.setId(developerDTO.getId());
        entity.setEmail(developerDTO.getEmail());
        entity.setFirstName(developerDTO.getFirstName());
        entity.setLastName(developerDTO.getLastName());
        entity.setProjectList(developerDTO.getProjectDTOList()
                .stream().map(projectMapping::mapToProjectEntity)
                .collect(Collectors.toSet()));
        return entity;
    }
}
