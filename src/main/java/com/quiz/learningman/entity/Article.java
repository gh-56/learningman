package com.quiz.learningman.entity;

import com.quiz.learningman.dto.ArticleForm;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String content;
    @ManyToOne
    Member member;

//    public Article toEntity(ArticleForm dto) {
//        this.title = dto.getTitle();
//    }

    public static Article createArticle(ArticleForm dto, Member member){
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setMember(member);
        return article;
    }

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
