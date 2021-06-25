package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.dto.utils.DepartmentMapping;
import com.springexample.springsecuritydemo.model.entity.Department;
import com.springexample.springsecuritydemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private final DepartmentRepository repository;
    private final DepartmentMapping departmentMapping;

    public DepartmentService(DepartmentRepository repository, DepartmentMapping departmentMapping) {
        this.repository = repository;
        this.departmentMapping = departmentMapping;
    }

    public Department saveDepartment (Department department){
        return repository.save(department);
    }

    public List<Department> saveDepartments(List<Department> departments) {
        return repository.saveAll(departments);
    }

    public List<DepartmentDTO> getDepartments(){
        return repository.findAll().stream().map(departmentMapping::mapToDepartmentDTO).collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id){
        return departmentMapping.mapToDepartmentDTO(repository.findById(id).orElse(null));
    }
    public DepartmentDTO getDepartmentByName(String name){
        return departmentMapping.mapToDepartmentDTO(repository.findDepartmentByName(name));
    }

    public void deleteDepartment(Long id){
        repository.deleteById(id);
    }

    public Department updateDepartment (Department department) {
        Department existingDepartment = repository.findById(department.getId()).orElse(null);
        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setDevelopers(department.getDevelopers());
        return repository.save(existingDepartment);
    }
}
