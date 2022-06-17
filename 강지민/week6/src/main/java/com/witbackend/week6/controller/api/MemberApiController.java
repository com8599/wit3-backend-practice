package com.witbackend.week6.controller.api;

import com.witbackend.week6.dto.MemberDTO.MemberRequestDTO;
import com.witbackend.week6.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "http://localhost:8080/members/list";
    
    // 등록 post 통신
    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postAdd(@RequestBody MemberRequestDTO memberRequestDTO, HttpServletResponse response) throws IOException {
        memberService.register(memberRequestDTO);
        response.sendRedirect(REDIRECT_TO_LIST);
    }

    // 수정 post 통신
    @PutMapping(value = "mod/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postMod(@RequestBody MemberRequestDTO memberRequestDTO, HttpServletResponse response) throws IOException {
        memberService.register(memberRequestDTO);
        response.sendRedirect(REDIRECT_TO_LIST);
    }

    // 삭제
    @DeleteMapping(value = "del/{id}")
    public void del(@PathVariable Long id, HttpServletResponse response) throws IOException {
        memberService.delete(id);
        response.sendRedirect(REDIRECT_TO_LIST);
    }

}
