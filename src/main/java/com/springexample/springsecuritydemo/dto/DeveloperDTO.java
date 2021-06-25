package com.springexample.springsecuritydemo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.springexample.springsecuritydemo.model.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;
@JsonSerialize
@JsonDeserialize
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DeveloperDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private  String email;
    private List<ProjectDTO> projectDTOList;

}
