package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.dto.DeveloperDTO;
import com.springexample.springsecuritydemo.model.entity.Developer;

import java.util.Optional;

public interface DeveloperQuery {
    DeveloperDTO findByEmail(String email);
}
