package com.quiz.learningman.service;

import com.quiz.learningman.dto.ArticleForm;
import com.quiz.learningman.entity.Article;
import com.quiz.learningman.entity.Comment;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.ArticleRepository;
import com.quiz.learningman.repository.CommentRepository;
import com.quiz.learningman.repository.MemberRepository;
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
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    // 퀴즈 생성
    public Article create(ArticleForm dto, String email) {
        Member member = memberRepository.findByMemberEmail(email);
        Article article = Article.createArticle(dto, member);
        return articleRepository.save(article);
    }

    // 퀴즈 목록
    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article editArticle(ArticleForm articleForm) {
        Article article = articleRepository.findById(articleForm.getId()).orElse(null);
        article.setTitle(articleForm.getTitle());
        article.setContent(articleForm.getContent());
        articleRepository.save(article);
        return article;
    }

    public void deleteArticle(ArticleForm articleForm) {
        List<Comment> allComment = commentRepository.findByArticleId(articleForm.getId());
        for (Comment comment : allComment) {
            commentRepository.delete(comment);
        }
        Article article = articleRepository.findById(articleForm.getId()).orElse(null);
        articleRepository.delete(article);
    }
}
