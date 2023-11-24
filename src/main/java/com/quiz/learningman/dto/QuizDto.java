package com.quiz.learningman.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuizDto {
    private Long quizId;
    private String quizQuestion;
    private String answer;
}
