package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Article;
import com.quiz.learningman.entity.Member;
import jakarta.persistence.OneToMany;
import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter @Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
}