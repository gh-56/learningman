package com.quiz.learningman.entity;

import com.quiz.learningman.constant.Result;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberPerform {
    @ManyToOne
    @JoinColumn(name = "member_email")
    Member member;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    Chapter chapterId;

    @Enumerated
    Result result;
}
