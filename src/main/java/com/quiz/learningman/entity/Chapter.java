package com.quiz.learningman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue
    private Long chapterId;
    @Column
    private String chapterName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;
}