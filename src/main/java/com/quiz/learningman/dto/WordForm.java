package com.quiz.learningman.dto;

import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class WordForm {
    private Long id;
    private String kWord;
    private String eWord;
}
