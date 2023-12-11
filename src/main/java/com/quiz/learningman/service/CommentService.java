package com.quiz.learningman.service;

import com.quiz.learningman.dto.CommentDto;
import com.quiz.learningman.entity.Article;
import com.quiz.learningman.entity.Comment;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.ArticleRepository;
import com.quiz.learningman.repository.CommentRepository;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto, String email){
        Member member = memberRepository.findByMemberEmail(email);
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article, member);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(CommentDto::createCommentDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findByCommentId(id);
        commentRepository.delete(comment);
    }

    public Comment edit(CommentDto commentDto) {
        Comment comment = commentRepository.findByCommentId(commentDto.getId());
        comment.setBody(commentDto.getBody());
        commentRepository.save(comment);
        return comment;
    }
}
