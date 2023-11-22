package com.quiz.learningman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Room {

    @Id
    @Column(name = "room_id")
    @GeneratedValue
    private Long id;


//    @JoinColumn(name = "member_id")
//    private
}
