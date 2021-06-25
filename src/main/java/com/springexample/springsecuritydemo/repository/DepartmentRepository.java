package com.springexample.springsecuritydemo.repository;

import com.springexample.springsecuritydemo.model.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findDepartmentByName (String name);
}
