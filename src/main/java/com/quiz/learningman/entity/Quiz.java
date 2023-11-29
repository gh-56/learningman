package com.quiz.learningman.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    @Column @NotNull @Size(max=5)
    private String vdx;

    @Column @NotNull @Size(max=255)
    private String book;
    @Column @Size(max=255)
    private String chapter;

    @Column @NotNull
    private String kor;
    @Column @NotNull
    private String eng;
}