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
public class DeveloperDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private  String email;
    private DepartmentDTO departmentDTO;
    private Set<ProjectDTO> projectDTOList;

}
