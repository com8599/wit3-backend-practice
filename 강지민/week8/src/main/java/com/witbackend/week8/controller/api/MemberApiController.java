package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.login.LoginDto;
import com.witbackend.week8.dto.login.MemberDto;
import com.witbackend.week8.dto.login.TokenDto;
import com.witbackend.week8.jwt.JwtFilter;
import com.witbackend.week8.jwt.TokenProvider;
import com.witbackend.week8.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberApiController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("signup")
    public ResponseEntity<MemberDto> signup(
            @Valid @RequestBody MemberDto memberDto
    ) {
        return ResponseEntity.ok(memberService.signup(memberDto));
    }

    @PostMapping("authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();

        String accessToken = tokenProvider.createToken(authentication);
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, accessToken);

        return new ResponseEntity<>(new TokenDto(accessToken), httpHeaders, HttpStatus.OK);
    }
}
