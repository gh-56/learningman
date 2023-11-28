package com.quiz.learningman.repository;

import com.quiz.learningman.entity.Member;
import com.quiz.learningman.entity.MemberProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberImgRepository extends JpaRepository<MemberProfileImg, Long> {
    @Query("SELECT m FROM MemberProfileImg  m " +
            "WHERE m.memberImgId = ?1")
    MemberProfileImg findByMemberImgIdJpql(@Param("memberImgId") Long memberImgId);
}
