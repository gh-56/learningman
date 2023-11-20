package com.quiz.learningman.service;

import com.quiz.learningman.dto.MemberDto;
import com.quiz.learningman.entity.Member;
import com.quiz.learningman.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        // 가입을 위해 입력한 멤버가 존재한다면
        if (findMember != null) {
            // 예외 발생
            throw new IllegalStateException("가입된 회원입니다.");
        }
    }

    // 회원가입
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 프로필 정보 및 이미지 불러오기


    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberEmail(memberEmail);

        if (member == null){
            return null;
//            throw new UsernameNotFoundException(memberEmail);
        }

        return User.builder()
                .username(member.getMemberName())
                .password(member.getMemberPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
