package com.quiz.learningman.controller;

import com.quiz.learningman.dto.HomeworkDto;
import com.quiz.learningman.entity.Homework;
import com.quiz.learningman.service.HomeworkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeworkController {
    private final HomeworkService homeworkService;
    @PostMapping(value = "/homework")
    public ResponseEntity setHomework(@RequestBody HomeworkDto homeworkDto) {
        Homework homework = new Homework();
        try {
            homework = homework.createHomework(homeworkDto);
            homeworkService.saveHomework(homework);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(homework);
    }
//
//    @GetMapping(value = "/homework")

}
