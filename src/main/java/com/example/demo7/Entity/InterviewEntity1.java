package com.example.demo7.Entity;

import com.example.demo7.Entity.StudentId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "Interview1")
@IdClass(StudentId.class)  // Composite Key (id, posid)
public class InterviewEntity1 {

    @Id
    private Integer id;

    @Id
    private String posid; // Add posid as part of composite key

    private String interviewStatus;

    // Getters and Setters
}
