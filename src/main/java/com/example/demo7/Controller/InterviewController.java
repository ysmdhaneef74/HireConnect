package com.example.demo7.Controller;

import com.example.demo7.Entity.InterviewEntity;
import com.example.demo7.Repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/Interviews")
public class InterviewController {

    private final InterviewRepository interviewRepository;

    @GetMapping("/getAllInterviews")
    public List<InterviewEntity> getAllInterviews() {
        return interviewRepository.findAll();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InterviewEntity> updateInterviewStatus(
            @PathVariable int id,
            @RequestBody InterviewEntity updatedInterview) {

        Optional<InterviewEntity> existingInterview = interviewRepository.findById(id);

        if (existingInterview.isPresent()) {
            InterviewEntity interview = existingInterview.get();

            // Update only the provided fields
            if (updatedInterview.getInterview_status() != null) {
                interview.setInterview_status(updatedInterview.getInterview_status());
            }

            interviewRepository.save(interview);
            return ResponseEntity.ok(interview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
