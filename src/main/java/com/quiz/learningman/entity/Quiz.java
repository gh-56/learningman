package com.quiz.learningman.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue
    private Long quizId;

    @Column
    @Size(max=5)
    private String vdx;

    @Column
    @Size(max=255)
    private String book;
    @Column
    @Size(max=255)
    private String chapter;

    @Column
    private String kor;
    @Column
    private String eng;
}