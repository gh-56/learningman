package com.quiz.learningman.entity;

import com.quiz.learningman.constant.Result;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberPerform {
    @Id
    @GeneratedValue
    private Long memberPerformId;

    @ManyToOne
    @JoinColumn(name = "member_email")
    Member member;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    Chapter chapterId;

    @Enumerated
    Result result;
}
