package com.quiz.learningman.controller;

import com.quiz.learningman.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping(value = "/book")
    public ResponseEntity<List<String>> getBookList() {
        return ResponseEntity.ok(quizService.getBooks());
    }

    @PostMapping(value = "/book/chapter")
    public ResponseEntity getChapterNames(@RequestBody String book) {
        List<String> chaptersByBook = quizService.getChaptersByBook(book);
        System.out.println("chaptersByBook : " + chaptersByBook);
        System.out.println(chaptersByBook.size());
        try {
            return ResponseEntity.status(HttpStatus.OK).body(chaptersByBook);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
