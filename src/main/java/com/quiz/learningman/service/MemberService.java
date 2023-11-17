package com.quiz.learningman.service;

import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 중복 이메일 검사
    private void checkExistingMember(Member member) {
        Member checkMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        if (checkMember != null) {
            throw new IllegalStateException("이미 가입한 회원입니다.");
        }
    }

    public Member saveMember(Member member){
        checkExistingMember(member);
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(memberEmail);

        if (member == null){
            throw new UsernameNotFoundException(memberEmail);
        }

        return User.builder()
                .username(member.getMemberName())
                .password(member.getMemberPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
