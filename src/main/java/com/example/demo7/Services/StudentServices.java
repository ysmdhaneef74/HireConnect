package com.example.demo7.Services;

import com.example.demo7.Entity.InterviewEntity;
import com.example.demo7.Entity.StudentEntity;
import com.example.demo7.Repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServices {
    private final StudentRepository studentRepository;
    public List<StudentEntity> getAllStud() {
        List<StudentEntity> StudtEntityList =  studentRepository.findAll();
        return StudtEntityList;
    }

    public Optional<StudentEntity> getByStudId(Integer id) {
        return studentRepository.findById(id);

    }
    public List<StudentEntity> getBySkillSet(String skillSet) {
        return studentRepository.findBySkillSet(skillSet);
    }
    public List<StudentEntity> getByMultipleSkills(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            return studentRepository.findAll(); // Return all if no skills are provided
        }

        List<StudentEntity> result = new ArrayList<>();
        for (String skill : skills) {
            result.addAll(studentRepository.findBySkillSet(skill));
        }

        return result.stream().distinct().collect(Collectors.toList());
    }

    public List<StudentEntity> getByExactSkillSet(String skillset) {
        return studentRepository.findByExactSkillSet(skillset);
    }



    public StudentEntity addDetails(StudentEntity emp) {
        return studentRepository.save(emp);
    }

    public StudentEntity updateStudent(Integer id, StudentEntity studentDetails) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(studentDetails.getName());
                    existingStudent.setSkill_set(studentDetails.getSkill_set());
                    existingStudent.setYear_of_exp(studentDetails.getYear_of_exp());
                    existingStudent.setPhoneno(studentDetails.getPhoneno());
                    existingStudent.setEmail(studentDetails.getEmail());
                    return studentRepository.save(existingStudent);
                })
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

    }


    public String deleteStudent(Integer id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student with ID: " + id + " has been deleted successfully!";
        } else {
            throw new RuntimeException("Student not found with ID: " + id);
        }
    }


    public StudentEntity updatePartialStudent(Integer id, Map<String, Object> updates) {
        return studentRepository.findById(id).map(existingStudent -> {
            updates.forEach((key, value) -> {
                switch (key) {
                    case "name" -> existingStudent.setName((String) value);
                    case "skill_set" -> existingStudent.setSkill_set((String) value);
                    case "year_of_exp" -> existingStudent.setYear_of_exp((Integer) value);
                    case "phoneno" -> existingStudent.setPhoneno((String) value);
                    case "email" -> existingStudent.setEmail((String) value);
                }
            });
            return studentRepository.save(existingStudent);
        }).orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }


    public List<Map<String, Object>> searchStudents(int year_of_exp, String skill_set, String interview_status) {
        List<Object[]> results = studentRepository.searchStudents(year_of_exp, skill_set, interview_status);

        List<Map<String, Object>> finalResults = new ArrayList<>();

        for (Object[] obj : results) {
            StudentEntity student = (StudentEntity) obj[0];
            InterviewEntity interview = (InterviewEntity) obj[1];

            Map<String, Object> studentInterviewMap = new HashMap<>();
            studentInterviewMap.put("id", student.getId());
            studentInterviewMap.put("name", student.getName());
            studentInterviewMap.put("skill_set", student.getSkill_set());
            studentInterviewMap.put("year_of_exp", student.getYear_of_exp());
            studentInterviewMap.put("phoneno", student.getPhoneno());
            studentInterviewMap.put("email", student.getEmail());
            studentInterviewMap.put("interview_status", interview.getInterview_status()); // Ensure getter exists

            finalResults.add(studentInterviewMap);
        }

        return finalResults;
    }

}
