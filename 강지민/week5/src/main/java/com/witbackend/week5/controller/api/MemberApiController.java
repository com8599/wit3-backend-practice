package com.witbackend.week5.controller.api;

import com.witbackend.week5.domain.Member;
import com.witbackend.week5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "redirect:/member/list";
    
    // 등록 post 통신
    @PostMapping(value = "add")
    public String postAdd(Member member) {
        memberService.register(member);
        return REDIRECT_TO_LIST;
    }

    // 수정 post 통신
    @PostMapping(value = "mod/{id}")
    public String postMod(Member member) {
        memberService.register(member);
        return REDIRECT_TO_LIST;
    }

    // 삭제
    @GetMapping("del/{id}")
    public String del(@PathVariable Long id) {
        memberService.delete(id);
        return REDIRECT_TO_LIST;
    }

}
