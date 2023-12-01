package com.quiz.learningman.service;

import com.quiz.learningman.entity.Homework;
import com.quiz.learningman.repository.HomeworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeworkService {
    private final HomeworkRepository homeworkRepository;

    public Homework saveHomework(Homework homework) {
        return homeworkRepository.save(homework);
    }
}
