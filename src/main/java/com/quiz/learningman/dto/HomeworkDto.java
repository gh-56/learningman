package com.quiz.learningman.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@NoArgsConstructor
public class HomeworkDto {
    Long homeworkId;

    private Date deadlineDate;

    private String homeworkBook;

    private String homeworkChapter;


}
