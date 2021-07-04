package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
@Table(name = "department")
@ToString(exclude = {"developers"})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String Description;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Developer> developers;

}
