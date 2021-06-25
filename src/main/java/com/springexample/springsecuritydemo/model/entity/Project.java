package com.springexample.springsecuritydemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
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
    @ManyToMany(mappedBy = "projectList", fetch = FetchType.EAGER)
    @JoinTable(name = "developers_projects",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developerSet;
}
