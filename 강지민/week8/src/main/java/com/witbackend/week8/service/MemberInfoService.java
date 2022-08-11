package com.witbackend.week8.service;

import com.witbackend.week8.domain.Authority;
import com.witbackend.week8.domain.MemberInfo;
import com.witbackend.week8.domain.RefreshToken;
import com.witbackend.week8.domain.Role;
import com.witbackend.week8.dto.login.MemberInfoDto;
import com.witbackend.week8.dto.login.RefreshTokenRequestDto;
import com.witbackend.week8.dto.login.TokenDto;
import com.witbackend.week8.exception.DuplicateMemberException;
import com.witbackend.week8.jwt.TokenProvider;
import com.witbackend.week8.repository.MemberInfoRepository;
import com.witbackend.week8.repository.RefreshTokenRepository;
import com.witbackend.week8.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static com.witbackend.week8.domain.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberInfoDto signup(MemberInfoDto memberInfoDto) {
        if (memberInfoRepository.findOneWithAuthoritiesByEmail(memberInfoDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName(ROLE_USER)     // enum 화 해주세요
                .build();

        MemberInfo memberInfo = MemberInfo.builder()
                .email(memberInfoDto.getEmail())
                .password(passwordEncoder.encode(memberInfoDto.getPassword()))
                .nickname(memberInfoDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberInfoDto.from(memberInfoRepository.save(memberInfo));
    }

    // 기존 토큰과 비교하여 없거나 토큰이 일치하지 않으면 재발급
    @Transactional
    public TokenDto reissueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String getToken = refreshTokenRequestDto.getRefreshToken();

        tokenProvider.validateToken(getToken);

        Authentication authentication = tokenProvider.getAuthentication(getToken);

        RefreshToken findTokenEntity = refreshTokenRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new RuntimeException("not find refresh Token"));

        if(!getToken.equals(findTokenEntity.getToken())) {
            throw new RuntimeException("not equals refresh token");
        }

        String newToken = tokenProvider.createRefreshToken(authentication);
        findTokenEntity.changeToken(newToken);

        return TokenDto.builder()
                .token(tokenProvider.createToken(authentication))
                .refreshToken(newToken)
                .build();
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getUserWithAuthorities(String email) {
        return MemberInfoDto.from(memberInfoRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
    }

    @Transactional(readOnly = true)
    public MemberInfoDto getMyUserWithAuthorities() {
        return MemberInfoDto.from(SecurityUtil.getCurrentUsername().flatMap(memberInfoRepository::findOneWithAuthoritiesByEmail).orElse(null));
    }
}
