package com.quiz.learningman.entity;

import com.quiz.learningman.dto.ArticleForm;
import com.quiz.learningman.dto.WordForm;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String kWord;
    @Column
    private String eWord;

    public static Word createWord(WordForm dto){
        Word word = new Word();
        word.setKWord(dto.getKWord());
        word.setEWord(dto.getEWord());
        return word;
    }

    public void patch(Word word) {
        if(word.kWord != null)
            this.kWord = word.kWord;
        if(word.eWord != null)
            this.eWord = word.eWord;
    }
}