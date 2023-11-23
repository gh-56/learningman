package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Chapter findByBook(String bookName);
}
