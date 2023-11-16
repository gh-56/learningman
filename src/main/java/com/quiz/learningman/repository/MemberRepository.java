package com.quiz.learningman.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.quiz.learningman.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

}
