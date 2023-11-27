package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Book;
import com.quiz.learningman.entity.Chapter;
import com.quiz.learningman.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Quiz findByChapterAndBook(Chapter chapter, Book book);
}
