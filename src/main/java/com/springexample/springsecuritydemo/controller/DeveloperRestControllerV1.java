package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.service.DeveloperService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DeveloperRestControllerV1 {

    private final DeveloperService service;

    public DeveloperRestControllerV1(DeveloperService service) {
        this.service = service;
    }


    @GetMapping("/developers")
    public List<DeveloperDTO> getAll(){
        return service.getDevelopers();
    }

    @GetMapping("/developers/{id}")
    @PreAuthorize("hasAuthority('developers.read')")
    public DeveloperDTO getById(@PathVariable Long id){
        return service.getDeveloperById(id);
    }

    @GetMapping("/developers/{email}")
    @PreAuthorize("hasAuthority('developers.read')")
    public DeveloperDTO getDeveloperByEmail(@PathVariable String email){
        return service.getDeveloperByEmail(email);
    }

    @GetMapping("/developers/{developerId}/projects")
    @PreAuthorize("hasAuthority('developers.read')")
    public List<ProjectDTO> getProjectsByDevelopersId(@PathVariable Long developerId){
        return service.getProjectsByDeveloperId(developerId);
    }

    @PostMapping("/developer")
    @PreAuthorize("hasAuthority('developers.write')")
    public Developer addDeveloper (@RequestBody DeveloperDTO developerDTO){
        return service.saveDeveloper(developerDTO);
    }

    @PostMapping("/developers")
    @PreAuthorize("hasAuthority('developers.write')")
    public List<Developer> addDevelopers (@RequestBody List<DeveloperDTO> developersDTO){
        return service.saveDevelopers(developersDTO);
    }

    @PatchMapping  ("/developers")
    @PreAuthorize("hasAuthority('developers.write')")
    public Developer updateDeveloper(@RequestBody DeveloperDTO developerDTO){
        return  service.updateDeveloper(developerDTO);
    }

    @DeleteMapping("/developers/{id}")
    @PreAuthorize("hasAuthority('developers.write')")
    public void deleteById(@PathVariable Long id){
        service.deleteDeveloper(id);
    }
}
