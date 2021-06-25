package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.model.entity.Developer;
import com.springexample.springsecuritydemo.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeveloperService {
    @Autowired
    private final DeveloperRepository repository;

    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public Developer saveDeveloper (Developer developer){
        return repository.save(developer);
    }

    public List<Developer> saveDevelopers(List<Developer> developers) {
        return repository.saveAll(developers);
    }

    public List<Developer> getDevelopers(){
        return  repository.findAll();
    }

    public Developer getDeveloperById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Developer getDeveloperByEmail(String email){
        return repository.findByEmail(email);
    }

    public void deleteDeveloper(Long id){
        repository.deleteById(id);
    }

    public Developer updateDeveloper (Developer developer){
        Developer existingDeveloper = repository.findById(developer.getId()).orElse(null);
        existingDeveloper.setEmail(developer.getEmail());
        existingDeveloper.setFirstName(developer.getFirstName());
        existingDeveloper.setLastName(developer.getLastName());
        return  repository.save(existingDeveloper);
    }
}
