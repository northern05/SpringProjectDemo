package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.model.entity.Department;
import com.springexample.springsecuritydemo.service.DepartmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DepartmentRestController {

    private DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getAll() {
        return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    @PreAuthorize("hasAuthority('departments.read')")
    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
        return departmentService.findDepartmentById(id);
    }

    @GetMapping("/departments/{name}")
    @PreAuthorize("hasAuthority('departments.read')")
    public DepartmentDTO getDepartmentById(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @PostMapping("/department")
    @PreAuthorize("hasAuthority('departments.write')")
    public Department addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.saveDepartment(departmentDTO);
    }

    @PostMapping("/departments")
    @PreAuthorize("hasAuthority('departments.write')")
    public List<Department> addDepartment(@RequestBody List<DepartmentDTO> departmentDTO) {
        return departmentService.saveDepartments(departmentDTO);
    }

    @PatchMapping("/departments")
    @PreAuthorize("hasAuthority('departments.write')")
    public Department updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(departmentDTO);
    }

    @DeleteMapping("/departments/{id}")
    @PreAuthorize("hasAuthority('departments.write')")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

}
