package com.witbackend.week8.controller.api;

import com.witbackend.week8.domain.RefreshToken;
import com.witbackend.week8.dto.login.LoginDto;
import com.witbackend.week8.dto.login.RefreshTokenRequestDto;
import com.witbackend.week8.dto.login.TokenDto;
import com.witbackend.week8.jwt.JwtFilter;
import com.witbackend.week8.jwt.TokenProvider;
import com.witbackend.week8.repository.RefreshTokenRepository;
import com.witbackend.week8.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/authenticate")
public class AuthApiController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberInfoService memberInfoService;

    @PostMapping
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String refreshToken = tokenProvider.createRefreshToken(authentication);

        refreshTokenRepository.findByUserId(loginDto.getUsername())
                .ifPresentOrElse(
                        tokenEntity -> tokenEntity.changeToken(refreshToken),
                        () -> refreshTokenRepository.save(RefreshToken.createToken(loginDto.getUsername(), refreshToken))
                );

        HttpHeaders httpHeaders = new HttpHeaders();

        String accessToken = tokenProvider.createToken(authentication);
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken, refreshToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/accessToken")
    public ResponseEntity<TokenDto> reissueAccessToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return ResponseEntity.ok(memberInfoService.reissueAccessToken(refreshTokenRequestDto));
    }
}
