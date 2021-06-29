package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String Description;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Developer> developers;

}
