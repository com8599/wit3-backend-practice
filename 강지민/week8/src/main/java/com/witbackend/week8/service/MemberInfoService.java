package com.witbackend.week8.service;

import com.witbackend.week8.domain.Authority;
import com.witbackend.week8.domain.MemberInfo;
import com.witbackend.week8.dto.login.MemberInfoDto;
import com.witbackend.week8.exception.DuplicateMemberException;
import com.witbackend.week8.repository.MemberInfoRepository;
import com.witbackend.week8.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberInfoService(MemberInfoRepository memberInfoRepository, PasswordEncoder passwordEncoder) {
        this.memberInfoRepository = memberInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public MemberInfoDto signup(MemberInfoDto memberInfoDto) {
        if (memberInfoRepository.findOneWithAuthoritiesByUsername(memberInfoDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        MemberInfo memberInfo = MemberInfo.builder()
                .username(memberInfoDto.getUsername())
                .password(passwordEncoder.encode(memberInfoDto.getPassword()))
                .nickname(memberInfoDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberInfoDto.from(memberInfoRepository.save(memberInfo));
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getUserWithAuthorities(String username) {
        return MemberInfoDto.from(memberInfoRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getMyUserWithAuthorities() {
        return MemberInfoDto.from(SecurityUtil.getCurrentUsername().flatMap(memberInfoRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }
}
