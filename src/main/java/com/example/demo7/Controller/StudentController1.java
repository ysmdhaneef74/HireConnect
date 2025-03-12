package com.example.demo7.Controller;

import com.example.demo7.DTO.StudentDTO1;
import com.example.demo7.Services.StudentServices1;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Student1")
@RequiredArgsConstructor
public class StudentController1 {

    private final StudentServices1 studentServices1;

    @PostMapping("/search")
    public List<StudentDTO1> searchStudents(@RequestBody StudentDTO1 request) {
        return studentServices1.searchStudents(request);
    }
}
