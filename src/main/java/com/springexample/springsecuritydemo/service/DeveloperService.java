package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.dto.utils.DeveloperMapping;
import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {
    @Autowired
    private final DeveloperRepository repository;
    @Autowired
    private final DeveloperMapping developerMapping;

    public DeveloperService(DeveloperRepository repository, DeveloperMapping developerMapping) {
        this.repository = repository;
        this.developerMapping = developerMapping;
    }

    public Developer saveDeveloper (Developer developer){
        return repository.save(developer);
    }

    public List<Developer> saveDevelopers(List<Developer> developers) {
        return repository.saveAll(developers);
    }

    public List<DeveloperDTO> getDevelopers(){
        return repository.findAll().stream().map(developerMapping::mapToDeveloperDTO).collect(Collectors.toList());
    }

    public DeveloperDTO getDeveloperById(Long id){
        return developerMapping.mapToDeveloperDTO(repository.findById(id).orElse(null));
    }

    public DeveloperDTO getDeveloperByEmail(String email){
        return developerMapping.mapToDeveloperDTO(repository.findByEmail(email));
    }

    public void deleteDeveloper(Long id){
        repository.deleteById(id);
    }

    public Developer updateDeveloper (Developer developer){
        Developer existingDeveloper = repository.findById(developer.getId()).orElse(null);
        existingDeveloper.setEmail(developer.getEmail());
        existingDeveloper.setFirstName(developer.getFirstName());
        existingDeveloper.setLastName(developer.getLastName());
        existingDeveloper.setProjectList(developer.getProjectList());
        existingDeveloper.setDepartment(developer.getDepartment());
        return  repository.save(existingDeveloper);
    }
}
