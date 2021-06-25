package com.springexample.springsecuritydemo.dto.utils;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.model.entity.Department;
import org.springframework.stereotype.Service;

@Service
public class DepartmentMapping {
    public DepartmentDTO mapToDepartmentDTO(Department department){
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setDescription(department.getDescription());
        //departmentDTO.setDeveloperDTOList(department.getDevelopers());
        return departmentDTO;
    }

    public Department mapToDepartmentEntity(DepartmentDTO departmentDTO){
        Department entity = new Department();
        entity.setId(departmentDTO.getId());
        entity.setName(departmentDTO.getName());
        entity.setDescription(departmentDTO.getDescription());
        //entity.setDevelopers(departmentDTO.getDeveloperDTOList());
        return entity;
    }
}
