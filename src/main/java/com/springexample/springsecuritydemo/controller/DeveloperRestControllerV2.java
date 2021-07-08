package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.model.enam.SortType;
import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.repository.impl.DeveloperQueryImpl;
import com.springexample.springsecuritydemo.service.DeveloperService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class DeveloperRestControllerV2 {

    private final DeveloperService service;

    public DeveloperRestControllerV2(DeveloperService service) {
        this.service = service;
    }


    @GetMapping("/developers")
    @PreAuthorize("hasAuthority('developers:read')")
    public Page<DeveloperDTO> getDevelopers(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "3") Integer pageSize,
                                            @RequestParam(required = false) Developer.SortField sortField,
                                            @RequestParam(defaultValue = "SortType.DESC") SortType sortType,
                                            @RequestParam(required = false) List<String> firstNamesFilter,
                                            @RequestParam(required = false) List<String> departmentNamesFilter
    ) {
        return service
                .getAllDevelopersQuery(page,
                        pageSize,
                        sortField,
                        sortType,
                        firstNamesFilter,
                        departmentNamesFilter);
    }

    @GetMapping("/developers/{email}")
    @PreAuthorize("hasAuthority('developers:read')")
    public DeveloperDTO getDeveloperByEmailQuery(String email) {
        return service.getDeveloperByEmailQuery(email);
    }

    @PatchMapping("/developers")
    @PreAuthorize("hasAuthority('developers:write')")
    public void updateDeveloperQuery(@RequestBody DeveloperDTO developerDTO) {
        service.updateDeveloperQuery(developerDTO);
    }

    @DeleteMapping("/developers/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public void deleteByIdQuery(@PathVariable Long id) {
        service.deleteDeveloperQuery(id);
    }
}
