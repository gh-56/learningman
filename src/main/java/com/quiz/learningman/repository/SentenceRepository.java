package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
    // 특정 게시글의 모든 댓글 조회
    @Query(value = "select * from sentence where word_id = :wordId", nativeQuery = true)
    List<Sentence> findByWordId(Long wordId);
}
