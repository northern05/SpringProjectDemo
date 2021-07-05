package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "developers")
@ToString(exclude = {"projectSet", "department"})
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private  String email;

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "developerSet", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Project> projectSet;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;
}
