package com.witbackend.week8.controller.api;

import com.witbackend.week8.dto.login.MemberInfoDto;
import com.witbackend.week8.service.MemberInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberInfoApiController {
    private final MemberInfoService memberInfoService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/test-redirect")
    public void testRedirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/api/user");
    }

    @PostMapping("/signup")
    public ResponseEntity<MemberInfoDto> signup(
            @Valid @RequestBody MemberInfoDto memberInfoDto
    ) {
        return ResponseEntity.ok(memberInfoService.signup(memberInfoDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<MemberInfoDto> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberInfoService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<MemberInfoDto> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(memberInfoService.getUserWithAuthorities(username));
    }
}
