package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    Developer findByEmail(String email);
}
