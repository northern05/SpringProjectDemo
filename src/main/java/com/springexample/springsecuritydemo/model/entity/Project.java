package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private  String description;
    @Column(name = "project_link")
    private String projectLink;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "developers_projects",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developerSet;
}
