package com.springexample.springsecuritydemo.model.entity;

import com.springexample.springsecuritydemo.model.enam.Role;
import com.springexample.springsecuritydemo.model.enam.Status;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;


}
