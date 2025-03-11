package com.example.demo7.Repository;

import com.example.demo7.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    @Query("SELECT s FROM StudentEntity s WHERE LOWER(s.skill_set) LIKE LOWER(CONCAT('%', :skill, '%'))")
    List<StudentEntity> findBySkillSet(@Param("skill") String skill);

    @Query("SELECT s FROM StudentEntity s WHERE " +
            "LOWER(s.skill_set) LIKE LOWER(CONCAT('%', :skill1, '%')) OR " +
            "LOWER(s.skill_set) LIKE LOWER(CONCAT('%', :skill2, '%')) OR " +
            "LOWER(s.skill_set) LIKE LOWER(CONCAT('%', :skill3, '%'))")
    List<StudentEntity> findByMultipleSkills(@Param("skill1") String skill1, @Param("skill2") String skill2, @Param("skill3") String skill3);

    @Query("SELECT s FROM StudentEntity s WHERE s.skill_set = :skillSet")
    List<StudentEntity> findByExactSkillSet(@Param("skillSet") String skillSet);


    void deleteById(Integer id);


    @Query("SELECT DISTINCT s, i FROM StudentEntity s " +
            "JOIN InterviewEntity i ON s.id = i.id " +
            "WHERE s.year_of_exp >= :year_of_exp " +
            "AND LOWER(s.skill_set) LIKE LOWER(CONCAT('%', :skill_set, '%')) " +
            "AND LOWER(i.interview_status) = LOWER(:interview_status)")
    List<Object[]> searchStudents(@Param("year_of_exp") int year_of_exp,
                                  @Param("skill_set") String skill_set,
                                  @Param("interview_status") String interview_status);


}
