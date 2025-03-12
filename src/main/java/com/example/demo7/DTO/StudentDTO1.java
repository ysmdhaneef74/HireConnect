package com.example.demo7.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO1 {
    private Integer id;
    private String posid;
    private String name;
    private String skillSet;
    private Integer yearOfExp;
    private String phoneno;
    private String email;
    private String interviewStatus;
}
