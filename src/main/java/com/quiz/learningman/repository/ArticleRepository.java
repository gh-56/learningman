package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
