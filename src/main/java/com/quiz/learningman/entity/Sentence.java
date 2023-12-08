package com.quiz.learningman.entity;

import com.quiz.learningman.dto.SentenceDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
    @Column
    private String nickname;
    @Column
    private String body;

    public static Sentence createSentence(SentenceDto dto, Word word) {
        // 예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getWordId() != word.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        // 엔티티 생성 및 반환
        return new Sentence(
                dto.getId(),
                word,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(SentenceDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if(dto.getNickname() != null) //수정할 닉네임 데이터가 있다면
            this.nickname = dto.getNickname(); // 내용 반영
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
