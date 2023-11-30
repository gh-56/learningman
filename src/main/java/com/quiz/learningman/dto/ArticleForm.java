package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter @Setter
public class ArticleForm {
    private String title; // 단어(한국어)를 받을 필드
    private String content; // 단어(영어)를 받을 필드


}