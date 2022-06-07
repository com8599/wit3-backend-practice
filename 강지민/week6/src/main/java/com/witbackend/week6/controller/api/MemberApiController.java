package com.witbackend.week6.controller.api;

import com.witbackend.week6.domain.Member;
import com.witbackend.week6.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberApiController {
    private final MemberService memberService;
    private static final String REDIRECT_TO_LIST = "redirect:/members/list";
    
    // 등록 post 통신
    @PostMapping(value = "add")
    public String postAdd(Member member) {
        memberService.register(member);
        return REDIRECT_TO_LIST;
    }

    // 수정 post 통신
    @PutMapping(value = "mod/{id}")
    public String postMod(Member member) {
        memberService.register(member);
        return REDIRECT_TO_LIST;
    }

    // 삭제
    @DeleteMapping("del/{id}")
    public String del(@PathVariable Long id) {
        memberService.delete(id);
        return REDIRECT_TO_LIST;
    }

}
