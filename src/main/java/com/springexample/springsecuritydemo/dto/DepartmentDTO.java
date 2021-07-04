package com.springexample.springsecuritydemo.dto;

import lombok.*;


import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String name;
    private String Description;
    private Set<DeveloperDTO> developerDTOList;
}
