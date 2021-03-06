package com.springexample.springsecuritydemo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.Set;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String departmentNameDTO;
    private Set<ProjectDTO> projectDTOList;

}
