package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Project;
import com.springexample.springsecuritydemo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findProjectByName(String name);
}
