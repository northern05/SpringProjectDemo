package com.springexample.springsecuritydemo.dto.utils;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.model.entity.Department;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class DepartmentMapping {

    private DeveloperMapping developerMapping;

    public DepartmentMapping(DeveloperMapping developerMapping) {
        this.developerMapping = developerMapping;
    }

    public DepartmentDTO mapToDepartmentDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        departmentDTO.setDeveloperDTOList(department.getDevelopers()
                .stream().map(developerMapping::mapToDeveloperDTO)
                .collect(Collectors.toSet()));
        return departmentDTO;
    }

    public Department mapToDepartmentEntity(DepartmentDTO departmentDTO){
        Department entity = new Department();
        entity.setId(departmentDTO.getId());
        entity.setName(departmentDTO.getName());
        entity.setDescription(departmentDTO.getDescription());
        entity.setDevelopers(departmentDTO.getDeveloperDTOList()
                .stream().map(developerMapping::mapToDeveloperEntity)
                .collect(Collectors.toSet()));
        return entity;
    }
}
