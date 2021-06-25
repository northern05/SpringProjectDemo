package com.springexample.springsecuritydemo.controller;

import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1")
public class DeveloperRestControllerV1 {

    @Autowired
    private DeveloperService service;


    @GetMapping("/developers")
    public List<Developer> getAll(){
        return service.getDevelopers();
    }

    @GetMapping("/developers/{id}")
    @PreAuthorize("hasAuthority('developers.read')")
    public Developer getById(@PathVariable Long id){
        return service.getDeveloperById(id);
    }

    @GetMapping("/developer/{name}")
    @PreAuthorize("hasAuthority('developers.read')")
    public  Developer getDeveloperByEmail(@PathVariable String email){
        return service.getDeveloperByEmail(email);
    }

    @PostMapping("/addDeveloper")
    @PreAuthorize("hasAuthority('developers.write')")
    public Developer addDeveloper (@RequestBody Developer developer){
        return service.saveDeveloper(developer);
    }
    @PostMapping("/addDevelopers")
    @PreAuthorize("hasAuthority('developers.write')")
    public List<Developer> addDevelopers (@RequestBody List<Developer> developers){
        return service.saveDevelopers(developers);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('developers.write')")
    public Developer updateDeveloper(@RequestBody Developer developer){
        return  service.updateDeveloper(developer);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('developers.write')")
    public void deleteById(@PathVariable Long id){
        service.deleteDeveloper(id);
    }
}
