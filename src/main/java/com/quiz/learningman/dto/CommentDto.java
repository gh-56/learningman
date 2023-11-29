package com.quiz.learningman.dto;

import com.quiz.learningman.entity.Comment;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter @ToString
public class CommentDto {
    private Long id;
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), // 댓글 엔티티의 id
                comment.getArticle().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getNickname(), // 댓글 엔티티의 nickname
                comment.getBody() // 댓글 엔티티의 body
        );
    }
}
