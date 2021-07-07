package com.springexample.springsecuritydemo.dto;

import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Long id;
    private String name;
    private String description;
    private String link;
    private String subject;
}
