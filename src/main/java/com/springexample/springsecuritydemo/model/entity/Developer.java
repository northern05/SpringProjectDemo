package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private  String email;

    @ManyToMany(mappedBy = "developerSet", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Project> projectSet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;
}
