package com.quiz.learningman.controller;

import com.quiz.learningman.dto.CommentDto;
import com.quiz.learningman.entity.Comment;
import com.quiz.learningman.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                                             @RequestBody CommentDto dto,
                                             Principal principal) {
        String email = principal.getName();
        // 서비스에 위임
        CommentDto createdDto = commentService.create(articleId, dto, email);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity comments(@PathVariable Long articleId) {
        List<CommentDto> dtos = null;
        try{
        // 서비스에 위임
        dtos = commentService.comments(articleId);

        } catch(Exception e){
            System.out.println("게시물 번호와 매칭되는 댓글리스트 불러오기 실패");
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/editcomment")
    public ResponseEntity editComments(@RequestBody CommentDto commentDto){
        Comment edit = commentService.edit(commentDto);
        return ResponseEntity.status(HttpStatus.OK).body(edit);
    }

    @PostMapping("/api/deletecomment")
    public void deleteComment(@RequestBody CommentDto commentDto){
        Long id = commentDto.getId();
        commentService.delete(id);
    }
}
