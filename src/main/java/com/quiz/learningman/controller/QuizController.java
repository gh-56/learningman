package com.quiz.learningman.controller;

import com.quiz.learningman.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping(value = "/book")
    public ResponseEntity<String> getBookList() {
        return ResponseEntity.ok(quizService.getBooks().toString());
    }

    @PostMapping(value = "/book/chapter")
    public ResponseEntity<String> getChapterNames(@RequestParam("book") String book) {
        return ResponseEntity.status(HttpStatus.OK).body(quizService.getChaptersByBook(book).toString());
    }
}
