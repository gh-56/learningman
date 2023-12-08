package com.quiz.learningman.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ScoreDto {
    String score;

    List<String> wrongIndexList;
}
