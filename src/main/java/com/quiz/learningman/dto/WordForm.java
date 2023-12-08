package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Word;
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
