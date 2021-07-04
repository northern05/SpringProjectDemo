package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.dto.utils.DepartmentMapping;
import com.springexample.springsecuritydemo.dto.utils.DeveloperMapping;
import com.springexample.springsecuritydemo.exception.DepartmentNotFoundException;
import com.springexample.springsecuritydemo.model.entity.Department;
import com.springexample.springsecuritydemo.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapping departmentMapping;
    private final DeveloperMapping developerMapping;
    //private final ModelMapper mapper;

    @Autowired
    public DepartmentService(DepartmentRepository repository,
                             DepartmentMapping departmentMapping,
                             DeveloperMapping developerMapping) {
        this.repository = repository;
        this.departmentMapping = departmentMapping;
        this.developerMapping = developerMapping;
        //this.mapper = mapper;
    }

    public Department saveDepartment(DepartmentDTO departmentDTO) {
        return repository.save(departmentMapping.mapToDepartmentEntity(departmentDTO));
    }

    public List<Department> saveDepartments(List<DepartmentDTO> departments) {
        return repository.saveAll(departments.stream()
                .map(departmentMapping::mapToDepartmentEntity).collect(Collectors.toSet()));
    }

    public List<DepartmentDTO> getDepartments() {
        return repository.findAll().stream().map(departmentMapping::mapToDepartmentDTO).collect(Collectors.toList());
//        List<DepartmentDTO> result = repository.findAll().stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//        log.info("IN getAll - {} of personnel found", result.size());
//        return result;
    }

    public DepartmentDTO findDepartmentById(Long id) {
        //return repository.findById(id).ifPresent(departmentMapping::mapToDepartmentDTO);
        return departmentMapping.mapToDepartmentDTO(repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Отдел c id= " + id + " не найден")));
    }

    public DepartmentDTO getDepartmentByName(String name) {
        return departmentMapping.mapToDepartmentDTO(repository.findDepartmentByName(name));
    }

    public void deleteDepartment(Long id) {
        repository.deleteById(id);
    }

    public Department updateDepartment(DepartmentDTO department) {
        Department existingDepartment = repository.findById(departmentMapping
                .mapToDepartmentEntity(department).getId()).orElse(null);
        existingDepartment.setName(department.getName());
        existingDepartment.setDescription(department.getDescription());
        existingDepartment.setDevelopers(department.getDeveloperDTOList().stream()
                .map(developerMapping::mapToDeveloperEntity).collect(Collectors.toSet()));
        return repository.save(existingDepartment);
    }

//    private DepartmentDTO convertToDto(Department entity) {
//        return mapper.map(entity, DepartmentDTO.class);
//    }
}
