package com.quiz.learningman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class Room {

    @Id
    @GeneratedValue
    private Long roomId;

    private String roomName;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Member teacher;

    @OneToMany(mappedBy = "room")
    private List<Member> students;
}
