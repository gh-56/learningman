//package com.quiz.learningman.entity;
//
//import jakarta.persistence.*;
//import lombok.NoArgsConstructor;
//
//import java.util.Date;
//
//@Entity
//@NoArgsConstructor
//public class Homework {
//    @Id
//    @GeneratedValue
//    private Long homeworkId;
//    @Column
//    private Date deadlineDate;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
//
//    @ManyToOne
//    @JoinColumn(name = "chapter_id")
//    private Chapter chapter;
//}
