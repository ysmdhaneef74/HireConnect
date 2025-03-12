package com.example.demo7.Repository;

import com.example.demo7.DTO.StudentDTO1;
import com.example.demo7.Entity.StudentEntity1;
import com.example.demo7.Entity.StudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository1 extends JpaRepository<StudentEntity1, StudentId> {

    @Query("SELECT new com.example.demo7.DTO.StudentDTO1(s.id, s.posid, s.name, s.skillSet, s.yearOfExp, s.phoneno, s.email, i.interviewStatus) " +
            "FROM StudentEntity1 s LEFT JOIN InterviewEntity1 i ON s.id = i.id AND s.posid = i.posid " +
            "WHERE (:posid IS NULL OR s.posid = :posid) " +
            "AND (:yearOfExp IS NULL OR s.yearOfExp >= :yearOfExp) " +
            "AND (:skillSet IS NULL OR LOWER(s.skillSet) LIKE LOWER(CONCAT('%', :skillSet, '%'))) " +
            "AND (:interviewStatus IS NULL OR i.interviewStatus = :interviewStatus)")
    List<StudentDTO1> searchStudents(@Param("posid") String posid,
                                     @Param("yearOfExp") Integer yearOfExp,
                                     @Param("skillSet") String skillSet,
                                     @Param("interviewStatus") String interviewStatus);

}
