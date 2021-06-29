package com.springexample.springsecuritydemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
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
