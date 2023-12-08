package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Word;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class WordForm {
    private Long id;
    private String kWord;
    private String eWord;

    public Word toEntity() {
        return new Word(id, kWord, eWord);
    }
}
