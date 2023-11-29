package com.quiz.learningman.service;

import com.quiz.learningman.dto.ArticleForm;
import com.quiz.learningman.entity.Article;
import com.quiz.learningman.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // 퀴즈 생성
    public Article create(ArticleForm dto) {
        Article article = Article.createArticle(dto);
        return articleRepository.save(article);
    }

    // 퀴즈 목록
    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
}
