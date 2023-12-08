package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Word;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface WordRepository extends CrudRepository<Word, Long> {
    @Override
    ArrayList<Word> findAll();
}
