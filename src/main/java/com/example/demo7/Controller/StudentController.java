package com.example.demo7.Controller;

import com.example.demo7.DTO.StudentDTO;
import com.example.demo7.Entity.EmployeeEntity;
import com.example.demo7.Entity.InterviewEntity;
import com.example.demo7.Entity.StudentEntity;
import com.example.demo7.Repository.StudentRepository;
import com.example.demo7.Services.StudentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Students")
public class StudentController {
    private final StudentServices studentServices;
    private final StudentRepository studentRepository;

    @GetMapping("/getAllStud")
    public List<StudentEntity> getAllStud() {
        System.out.println("get all students details");
        return studentServices.getAllStud();
    }

    @GetMapping("/getAllStud/{id}")
    public Optional<StudentEntity> getByStudId(@PathVariable Integer id) {
        System.out.println("getById called");
        return studentServices.getByStudId(id);
    }

    @GetMapping("/getBySkill/{skillset}")
    public List<StudentEntity> getBySkillSet(@PathVariable String skillset) {
        return studentServices.getBySkillSet(skillset);
    }

    @GetMapping("/getByMultipleSkills")
    public List<StudentEntity> getByMultipleSkills(@RequestParam List<String> skills) {
        return studentServices.getByMultipleSkills(skills);
    }


    @GetMapping("/getByExactSkill/{skillset}")
    public List<StudentEntity> getByExactSkill(@PathVariable String skillset) {
        return studentServices.getByExactSkillSet(skillset);
    }


    @PostMapping("/postStud")
    public ResponseEntity<StudentEntity> createStudent(@RequestBody StudentEntity emp) {
        StudentEntity savedEmployee = studentServices.addDetails(emp);
        System.out.println("savedEmployee" + "  " + savedEmployee);
        return ResponseEntity.ok(savedEmployee);
    }

    @PutMapping("/updateStud/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Integer id, @RequestBody StudentEntity studentDetails) {
        StudentEntity updatedStudent = studentServices.updateStudent(id, studentDetails);
        return ResponseEntity.ok(updatedStudent);
    }

    @PatchMapping("/updatePartial/{id}")
    public ResponseEntity<StudentEntity> updatePartialStudent(
            @PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        StudentEntity updatedStudent = studentServices.updatePartialStudent(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }


    @DeleteMapping("/deleteStud/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        String message = studentServices.deleteStudent(id);
        return ResponseEntity.ok(message);
    }



    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchStudents(
            @RequestParam int year_of_exp,
            @RequestParam String skill_set,
            @RequestParam String interview_status) {
        List<Map<String, Object>> students = studentServices.searchStudents(year_of_exp, skill_set, interview_status);
        return ResponseEntity.ok(students);
    }

}

