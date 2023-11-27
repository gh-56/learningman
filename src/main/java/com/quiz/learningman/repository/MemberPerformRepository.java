package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Chapter;
import com.quiz.learningman.entity.MemberPerform;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPerformRepository extends JpaRepository<MemberPerform, Long> {
    MemberPerform findByChapterIdAndMemberPerformId(Chapter chapterId, Long memberPerformId);
}
