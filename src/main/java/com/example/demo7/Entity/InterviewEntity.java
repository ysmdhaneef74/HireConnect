package com.example.demo7.Entity;

import com.example.demo7.Entity.StudentEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Getter
@Setter
@Table(name="Interviews")
@Entity
public class InterviewEntity {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="interview_status")
    private String interview_status;
}

