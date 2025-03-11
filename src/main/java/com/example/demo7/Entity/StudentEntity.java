package com.example.demo7.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Students")
public class StudentEntity {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "skillset")
    private String skill_set;

    @Column(name = "year_of_exp")
    private int year_of_exp;

    @Column(name = "phoneno")
    private String phoneno;

    @Column(name = "email")
    private String email;



}
