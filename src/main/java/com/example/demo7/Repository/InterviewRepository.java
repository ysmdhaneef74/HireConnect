package com.example.demo7.Repository;

import com.example.demo7.Entity.InterviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<InterviewEntity, Integer> {
}
