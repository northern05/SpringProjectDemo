package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.dto.utils.DeveloperMapping;
import com.springexample.springsecuritydemo.dto.utils.ProjectMapping;
import com.springexample.springsecuritydemo.exception.DeveloperNotFoundException;
import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.repository.DeveloperRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperService {

    private final DeveloperRepository repository;
    private final DeveloperMapping developerMapping;
    private final ProjectMapping projectMapping;

    public DeveloperService(DeveloperRepository repository,
                            DeveloperMapping developerMapping,
                            ProjectMapping projectMapping) {
        this.repository = repository;
        this.developerMapping = developerMapping;
        this.projectMapping = projectMapping;
    }

    public Developer saveDeveloper(DeveloperDTO developer) {
        return repository.save(developerMapping.mapToDeveloperEntity(developer));
    }

    public List<Developer> saveDevelopers(List<DeveloperDTO> developersDTO) {
        //return developersDTO.forEach(developerDTO -> repository.save(developerMapping.mapToDeveloperEntity(developerDTO)));
        return repository.saveAll(developersDTO.stream()
                .map(developerMapping::mapToDeveloperEntity).collect(Collectors.toList()));
    }

    public List<DeveloperDTO> getDevelopers() {
        return repository.findAll().stream().map(developerMapping::mapToDeveloperDTO).collect(Collectors.toList());
    }

    public DeveloperDTO getDeveloperById(Long id) {
        return developerMapping.mapToDeveloperDTO(repository.findById(id)
                .orElseThrow(() -> new DeveloperNotFoundException("Разработчик c id= " + id + " не найден")));
    }

    public DeveloperDTO getDeveloperByEmail(String email) {
        return developerMapping.mapToDeveloperDTO(repository.findByEmail(email));
    }

    public List<ProjectDTO> getProjectsByDeveloperId(Long id) {
        return repository.getById(id).getProjectSet().stream()
                .map(projectMapping::mapToProjectDTO).collect(Collectors.toList());
    }

    public void deleteDeveloper(Long id) {
        repository.deleteById(id);
    }

    public Developer updateDeveloper(DeveloperDTO developerDTO) {
        Developer existingDeveloper = repository.findById(developerMapping
                .mapToDeveloperEntity(developerDTO).getId()).orElse(null);
        existingDeveloper.setEmail(developerMapping.mapToDeveloperEntity(developerDTO).getEmail());
        existingDeveloper.setFirstName(developerMapping.mapToDeveloperEntity(developerDTO).getFirstName());
        existingDeveloper.setLastName(developerMapping.mapToDeveloperEntity(developerDTO).getLastName());
        existingDeveloper.setProjectSet(developerMapping.mapToDeveloperEntity(developerDTO).getProjectSet());
        existingDeveloper.setDepartment(developerMapping.mapToDeveloperEntity(developerDTO).getDepartment());
        return repository.save(existingDeveloper);
    }
}
