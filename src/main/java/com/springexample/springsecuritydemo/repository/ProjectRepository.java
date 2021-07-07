package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findProjectBySubject(String subject, Pageable pageable);
    Page<Project> getProjectById(Long id, Pageable pageable);
    Project findProjectByName(String name);
}
