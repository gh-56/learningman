package com.quiz.learningman.service;

import com.quiz.learningman.dto.SentenceDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.Sentence;
import com.quiz.learningman.entity.Word;
import com.quiz.learningman.repository.MemberRepository;
import com.quiz.learningman.repository.SentenceRepository;
import com.quiz.learningman.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SentenceService {
    private final SentenceRepository sentenceRepository;
    private final WordRepository wordRepository;
    private final MemberRepository memberRepository;

    public List<SentenceDto> sentenceList(Long wordId) {
        return sentenceRepository.findByWordId(wordId)
                .stream()
                .map(sentence -> SentenceDto.createSentenceDto(sentence))
                .collect(Collectors.toList());
    }

    @Transactional
    public SentenceDto create(Long wordId, SentenceDto dto, Member member) {
        // 1. 게시글 조회 및 예외 발생
        Word word = wordRepository.findById(wordId)
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패! " +
                        "대상 게시글이 없습니다."));

        // 2. 댓글 엔티티 생성
        Sentence sentence = Sentence.createSentence(dto, word, member);
        // 3. 댓글 엔티티를 DB에 저장
        Sentence created = sentenceRepository.save(sentence);
        // 4. DTO로 변환해 반환
        return SentenceDto.createSentenceDto(created);
    }

    @Transactional
    public SentenceDto update(Long id, SentenceDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Sentence target = sentenceRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("댓글 수정 실패!" +
                        "대상 댓글이 없습니다."));
        // 2. 댓글 수정
        target.patch(dto);

        // 3. DB로 갱신
        Sentence updated = sentenceRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return SentenceDto.createSentenceDto(updated);
    }

    @Transactional
    public SentenceDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Sentence target = sentenceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
                        "대상이 없습니다."));
        // 2. 댓글 삭제
        sentenceRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return SentenceDto.createSentenceDto(target);
    }
}