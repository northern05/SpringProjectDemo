package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findProjectByName(String name);
}
