package com.witbackend.week8.service;

import com.witbackend.week8.domain.Authority;
import com.witbackend.week8.domain.MemberInfo;
import com.witbackend.week8.domain.RefreshToken;
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

@Service
@RequiredArgsConstructor
public class MemberInfoService {
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

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

    // 기존 토큰과 비교하여 없거나 토큰이 일치하지 않으면 재발급
    @Transactional
    public TokenDto reissueAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String resolveToken = resolveToken(refreshTokenRequestDto.getRefreshToken());

        tokenProvider.validateToken(resolveToken);

        Authentication authentication = tokenProvider.getAuthentication(resolveToken);

        RefreshToken findTokenEntity = refreshTokenRepository.findByUserId(authentication.getName())
                .orElseThrow(() -> new RuntimeException("not find refresh Token"));

        if(!resolveToken.equals(findTokenEntity.getToken())) {
            throw new RuntimeException("not equals refresh token");
        }

        String newToken = tokenProvider.createRefreshToken(authentication);
        findTokenEntity.changeToken(newToken);

        return TokenDto.builder()
                .token("Bearer " + tokenProvider.createToken(authentication))   // Bearer 은 프론트와 백엔드가 약속한 문구이므로 불필요하므로 제거
                .refreshToken("Bearer " + newToken)
                .build();
    }

    // "Bearer " 되있는 토큰을 순수 토큰으로 변환
    // -> Bearer 삭제
    private String resolveToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        throw new RuntimeException("not valid refresh token!!");
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
