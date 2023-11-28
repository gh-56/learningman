package com.quiz.learningman.controller;

import com.quiz.learningman.dto.ArticleForm;
import com.quiz.learningman.entity.Article;
import com.quiz.learningman.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    // POST 문제 등록
    @PostMapping("/api/articles")
    public ResponseEntity create(@RequestBody ArticleForm dto){
        Article created = null;
        try {
            created = articleService.create(dto);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("/api/articles")
    public ResponseEntity index(){
        List<Article> index = articleService.index();
        return ResponseEntity.status(HttpStatus.OK).body(index);
    }
}
