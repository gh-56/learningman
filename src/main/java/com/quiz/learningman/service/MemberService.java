package com.quiz.learningman.service;

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

    public Member saveMember(Member member){
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException{
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
