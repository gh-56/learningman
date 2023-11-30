package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query(value = "SELECT DISTINCT q.book FROM Quiz q", nativeQuery = true)
    List<String> findDistinctBooks();
    @Query(value = "SELECT DISTINCT q.chapter_id FROM Quiz q WHERE q.book = :selectedBook", nativeQuery = true)
    List<String> findDistinctChaptersByBook(@Param("selectedBook") String book);
    @Query(value = "SELECT q.kor, q.eng FROM Quiz q WHERE q.book = :selectedBook AND q.chapter = :selectedChapter", nativeQuery = true)
    List<Object[]> findKorAndEngByBookAndChapter(@Param("selectedBook") String book, @Param("selectedChapter") String chapter);
}
