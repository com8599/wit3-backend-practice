package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.login.MemberInfoDto;
import com.witbackend.week8.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memberinfo")
public class MemberInfoApiController {
    private final MemberInfoService memberInfoService;

    @PostMapping("/signup")
    public ResponseEntity<MemberInfoDto> signup(
            @Valid @RequestBody MemberInfoDto memberInfoDto
    ) {
        return ResponseEntity.ok(memberInfoService.signup(memberInfoDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberInfoDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberInfoService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberInfoDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(memberInfoService.getUserWithAuthorities(username));
    }
}
