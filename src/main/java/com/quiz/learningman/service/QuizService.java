package com.quiz.learningman.service;

import com.quiz.learningman.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Transactional(readOnly = true)
    public List<String> getBooks() {
        return quizRepository.findDistinctBooks();
    }
    @Transactional(readOnly = true)
    public List<String> getChaptersByBook(String book) {
        return quizRepository.findDistinctChaptersByBook(book);
    }
    @Transactional(readOnly = true)
    public List<Object[]> getQuizListByBookIdAndChapterId (String book, String chapter) {
        return quizRepository.findKorAndEngByBookAndChapter(book, chapter);
    }
}
