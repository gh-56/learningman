package com.quiz.learningman.service;

import com.quiz.learningman.dto.WordForm;
import com.quiz.learningman.entity.Word;
import com.quiz.learningman.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WordService {
    @Autowired
    private WordRepository wordRepository;

    public List<Word> index() {
        return wordRepository.findAll();
    }

    public Word show(Long id) {
        return wordRepository.findById(id).orElse(null);
    }

    public Word create(WordForm dto) {
        Word word = Word.createWord(dto);
        if(word.getId() != null){
            return null;
        }
        return wordRepository.save(word);
    }

    public Word update(Long id, WordForm dto) {
        // 1. DTO -> 엔티니 변환하기
        Word word = Word.createWord(dto);
        log.info("id: {}, word: {}", id, word.toString());
        // 2. 타깃 조회하기
        Word target = wordRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != word.getId()){
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, word: {}", id, word.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(word);
        Word updated = wordRepository.save(target);
        return updated;
    }

    public Word delete(Long id) {
        // 1. 대상 찾기
        Word target = wordRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        wordRepository.delete(target);
        return target;
    }
}