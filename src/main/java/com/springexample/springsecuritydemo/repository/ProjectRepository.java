package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByName(String name);

    Optional<Project> getProjectById(Long id);
}
