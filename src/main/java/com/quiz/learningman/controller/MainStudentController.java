package com.quiz.learningman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainStudentController {
    @GetMapping("/api/register")
    public ResponseEntity register(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/register")
    public String register2(){
        return "hello spring";
    }
}
