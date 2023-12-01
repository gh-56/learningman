package com.quiz.learningman.entity;

import com.quiz.learningman.dto.HomeworkDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long homeworkId;
    @Column
    private Date deadlineDate;

    @Column
    private String homeworkBook;
    @Column
    private String homeworkChapter;

    public static Homework createHomework(HomeworkDto homeworkDto) {
        Homework homework = new Homework();
        homework.setHomeworkBook(homeworkDto.getHomeworkBook());
        homework.setHomeworkChapter(homeworkDto.getHomeworkChapter());
        homework.setDeadlineDate(homeworkDto.getDeadlineDate());
        return homework;
    }
}
