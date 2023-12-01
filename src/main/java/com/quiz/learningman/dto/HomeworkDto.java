package com.quiz.learningman.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class HomeworkDto {
    private Long homeworkId;
    private Date deadlineDate;
    private String homeworkBook;
    private String homeworkChapter;


}
