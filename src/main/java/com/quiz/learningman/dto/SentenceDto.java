package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Sentence;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class SentenceDto {
    private Long id;
    private Long wordId;
    private String nickname;
    private String body;

    public static SentenceDto createSentenceDto(Sentence sentence) {
        return new SentenceDto(
                sentence.getId(),
                sentence.getWord().getId(),
                sentence.getNickname(),
                sentence.getBody()
        );
    }
}
