package com.springexample.springsecuritydemo.model.entity;

import com.springexample.springsecuritydemo.model.enam.Role;
import com.springexample.springsecuritydemo.model.enam.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private  String email;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
