package com.springexample.springsecuritydemo.service;

import com.springexample.springsecuritydemo.dto.ProjectDTO;
import com.springexample.springsecuritydemo.dto.utils.ProjectMapping;
import com.springexample.springsecuritydemo.exception.ProjectNotFoundException;
import com.springexample.springsecuritydemo.model.entity.Project;
import com.springexample.springsecuritydemo.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapping projectMapping;

    public ProjectService(ProjectRepository repository, ProjectMapping projectMapping) {
        this.repository = repository;
        this.projectMapping = projectMapping;
    }

    public Project saveProject(ProjectDTO projectDTO) {
        return repository.save(projectMapping.mapToProjectEntity(projectDTO));
    }

    public List<Project> saveProjects(List<ProjectDTO> projectDTOList) {
        return repository.saveAll(projectDTOList.stream()
                .map(projectMapping::mapToProjectEntity).collect(Collectors.toSet()));
    }

    public ResponseEntity getProjects(String subject, Integer page, Integer size, Pageable pageable) {
        try {
            Pageable paging = PageRequest.of(page, size, pageable.getSort());

            Page<Project> projectPage;
            if (subject == null)
                projectPage = repository.findAll(paging);
            else
                projectPage = repository.findProjectBySubject(subject, paging);

            List<ProjectDTO> projectList = projectPage.getContent().stream()
                    .map(projectMapping::mapToProjectDTO).collect(Collectors.toList());
            Map<String, Object> response = new HashMap<>();
            response.put("projectList", projectList);
            response.put("currentPage", projectPage.getNumber());
            response.put("totalItems", projectPage.getTotalElements());
            response.put("totalPages", projectPage.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return  new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    public ProjectDTO getProjectById(Long id) {
//        return projectMapping.mapToProjectDTO(repository.findById(id)
//                .orElseThrow(() -> new ProjectNotFoundException("Проект с id = " + id + " не найден")));
//    }

    public ProjectDTO getProjectByName(String name) {
        return projectMapping.mapToProjectDTO(repository.findProjectByName(name));
    }

    public void deleteProject(Long id) {
        repository.deleteById(id);
    }

    public Project updateProject(ProjectDTO projectDTO) {
        Project existingProject = repository.findById(projectMapping
                .mapToProjectEntity(projectDTO).getId()).orElse(null);
        existingProject.setName(projectDTO.getName());
        existingProject.setDescription(projectDTO.getDescription());
        existingProject.setProjectLink(projectDTO.getLink());
        return repository.save(existingProject);
    }
}
