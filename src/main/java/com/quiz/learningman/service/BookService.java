package com.quiz.learningman.service;

import com.quiz.learningman.dto.ChapterDto;
import com.quiz.learningman.repository.BookRepository;
import com.quiz.learningman.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ChapterRepository chapterRepository;

//    public Long chapters(List<ChapterDto> chapterDtoList, Long bookId) {
//
//    }
}
