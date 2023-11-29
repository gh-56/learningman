package com.quiz.learningman.controller;

import com.quiz.learningman.dto.CommentDto;
import com.quiz.learningman.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/api/articles/{id}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long id,
                                             @RequestBody CommentDto dto) {
        // 서비스에 위임
        CommentDto createdDto = commentService.create(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
}
