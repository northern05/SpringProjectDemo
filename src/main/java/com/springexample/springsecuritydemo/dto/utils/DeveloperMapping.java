package com.springexample.springsecuritydemo.dto.utils;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.model.entity.Developer;
import org.springframework.stereotype.Service;

@Service
public class DeveloperMapping {
    public DeveloperDTO mapToDeveloperDTO (Developer developer){
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(developer.getId());
        dto.setEmail(developer.getEmail());
        dto.setFirstName(developer.getFirstName());
        dto.setLastName(developer.getLastName());
        //dto.setProjectDTOList(developer.getProjectList());
        return  dto;
    }

    public Developer mapToDeveloperEntity(DeveloperDTO developerDTO){
        Developer entity = new Developer();
        entity.setId(developerDTO.getId());
        entity.setEmail(developerDTO.getEmail());
        entity.setFirstName(developerDTO.getFirstName());
        entity.setLastName(developerDTO.getLastName());
        //entity.setDepartment(developerDTO.getProjectDTOList());
        return entity;
    }
}
