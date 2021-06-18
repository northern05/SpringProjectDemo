package com.springexample.springsecuritydemo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "developers")
public class Developer extends BaseEntity{

}
