package com.quiz.learningman.controller;

import com.quiz.learningman.dto.ArticleForm;
import com.quiz.learningman.entity.Article;
import com.quiz.learningman.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    // POST 퀴즈 등록
    @PostMapping("/api/articles")
    public ResponseEntity create(@RequestBody ArticleForm dto, Principal principal){
        Article created = null;
        String email = principal.getName();
        try {
            created = articleService.create(dto,email);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    // 퀴즈 목록 리스트 불러오기
    @GetMapping("/api/articles")
    public ResponseEntity index(){
        List<Article> index = articleService.index();
        return ResponseEntity.status(HttpStatus.OK).body(index);
    }
    // 퀴즈 상세 보기
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    @PostMapping("/api/editarticles")
    public ResponseEntity editArticle(@RequestBody ArticleForm articleForm){
        Article article = articleService.editArticle(articleForm);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    @PostMapping("/api/deletearticles")
    public ResponseEntity deleteArticle(@RequestBody ArticleForm articleForm){
        articleService.deleteArticle(articleForm);
        return null;
    }

}
