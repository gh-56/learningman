package com.quiz.learningman.controller;

import com.quiz.learningman.dto.SentenceDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.MemberRepository;
import com.quiz.learningman.service.SentenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SentenceController {
    // 댓글 생성
    private final SentenceService sentenceService;
    private final MemberRepository memberRepository;
    // 1. 댓글 조회
    @GetMapping("/api/words/{wordId}/sentence")
    public ResponseEntity<List<SentenceDto>> sentenceList(@PathVariable Long wordId){
        // 서비스에 위임
        List<SentenceDto> dtos = sentenceService.sentenceList(wordId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // 2. 댓글 생성
    @PostMapping("/api/words/{wordId}/sentence")
    public ResponseEntity<SentenceDto> create(@PathVariable Long wordId, @RequestBody SentenceDto dto, Principal principal){
        String email = principal.getName();
        Member member = memberRepository.findByMemberEmail(email);
        // 서비스에 위임
        SentenceDto createdDto = sentenceService.create(wordId, dto, member);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    // 3. 댓글 수정
    @PatchMapping("/api/sentence/{id}")
    public ResponseEntity<SentenceDto> update(@PathVariable Long id, @RequestBody SentenceDto dto){
        // 서비스에 위임
        SentenceDto updatedDto = sentenceService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }
    // 4. 댓글 삭제
    @DeleteMapping("/api/sentence/{id}")
    public ResponseEntity<SentenceDto> delete(@PathVariable Long id){
        // 서비스에 위임
        SentenceDto deletedDto = sentenceService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
