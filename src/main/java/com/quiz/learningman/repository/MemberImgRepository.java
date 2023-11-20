package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberImgRepository extends JpaRepository<MemberProfileImg, Long> {
}
