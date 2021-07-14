package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.DepartmentDTO;
import com.springexample.springsecuritydemo.model.entity.Department;
import com.springexample.springsecuritydemo.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Departments/V1")
@RestController
@RequestMapping("/api/v1")
public class DepartmentRestController {

    private DepartmentService departmentService;

    public DepartmentRestController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ApiOperation(value = "This method is used to get all departments.")
    @GetMapping("/departments")
    public List<DepartmentDTO> getAll() {
        return departmentService.getDepartments();
    }

//    @GetMapping("/departments/{id}")
//    @PreAuthorize("hasAuthority('departments.read')")
//    public DepartmentDTO getDepartmentById(@PathVariable Long id) {
//        return departmentService.findDepartmentById(id);
//    }

    @ApiOperation(value = "This method is used to get department by name.")
    @GetMapping("/departments/{name}")
    @PreAuthorize("hasAuthority('departments:read')")
    public DepartmentDTO getDepartmentById(@PathVariable String name) {
        return departmentService.getDepartmentByName(name);
    }

    @ApiOperation(value = "This method is used to adding department.")
    @PostMapping("/department")
    @PreAuthorize("hasAuthority('departments:write')")
    public Department addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.saveDepartment(departmentDTO);
    }

    @ApiOperation(value = "This method is used to adding list departments.")
    @PostMapping("/departments")
    @PreAuthorize("hasAuthority('departments:write')")
    public List<Department> addDepartment(@RequestBody List<DepartmentDTO> departmentDTO) {
        return departmentService.saveDepartments(departmentDTO);
    }

    @ApiOperation(value = "This method is used to update department.")
    @PatchMapping("/departments")
    @PreAuthorize("hasAuthority('departments:write')")
    public Department updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(departmentDTO);
    }

    @ApiOperation(value = "This method is used to delete department.")
    @DeleteMapping("/departments/{id}")
    @PreAuthorize("hasAuthority('departments:write')")
    public void deleteDepartmentById(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }

}
