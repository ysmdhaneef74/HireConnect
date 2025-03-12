package com.example.demo7.Services;

import com.example.demo7.DTO.StudentDTO1;
import com.example.demo7.Repository.StudentRepository1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServices1 {

    private final StudentRepository1 studentRepository;

    public  List<StudentDTO1> searchStudents(StudentDTO1 request) {
        return studentRepository.searchStudents(
                request.getPosid(),
                request.getYearOfExp(),
                request.getSkillSet(),
                request.getInterviewStatus()
        );
    }
}
