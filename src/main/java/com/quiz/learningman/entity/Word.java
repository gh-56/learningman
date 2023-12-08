package com.quiz.learningman.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String kWord;
    @Column
    private String eWord;

    public void patch(Word word) {
        if(word.kWord != null)
            this.kWord = word.kWord;
        if(word.eWord != null)
            this.eWord = word.eWord;
    }
}