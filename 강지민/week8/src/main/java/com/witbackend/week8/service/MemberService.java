package com.witbackend.week8.service;

import com.witbackend.week8.domain.Authority;
import com.witbackend.week8.domain.Member;
import com.witbackend.week8.dto.login.MemberDto;
import com.witbackend.week8.exception.DuplicateMemberException;
import com.witbackend.week8.repository.MemberRepository;
import com.witbackend.week8.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.witbackend.week8.domain.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName(ROLE_USER)
                .build();

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .nickname(memberDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    @Transactional(readOnly = true)
    public MemberDto getUserWithAuthorities(String email) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }
}
