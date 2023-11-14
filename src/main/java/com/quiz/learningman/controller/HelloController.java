package com.quiz.learningman.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "서버에서 응답하는 글자";
    }

    @GetMapping("/hello2")
    public ResponseEntity hello2() {
        return ResponseEntity.status(HttpStatus.OK).body("안녕하세요");
    }

    @GetMapping("/hello3")
    public ResponseEntity hello3() {
        List list = new ArrayList<>();

        list.add(Map.of("key", "value1"));
        list.add(Map.of("key", "value2"));
        list.add(Map.of("key", "value3"));

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
