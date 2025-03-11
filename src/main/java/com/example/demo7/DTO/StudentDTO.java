package com.example.demo7.DTO;

public class StudentDTO {
    private int id;
    private String skill_set;
    private String name;
    private int year_of_exp;
    private String phoneno;
    private String email;
    private String interview_status;

    public StudentDTO(int id,String skill_set, String name, int year_of_exp, String phoneno, String email , String interview_status) {
        this.id = id;
        this.skill_set = skill_set;
        this.name = name;


        this.year_of_exp = year_of_exp;
        this.phoneno = phoneno;
        this.email = email;
        this.interview_status = interview_status;
    }

    // Getters and Setters
}
