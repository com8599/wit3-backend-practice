package com.witbackend.week6.controller.view;

import com.witbackend.week6.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "members")
public class MemberViewController {
    private final MemberService memberService;

    // 목록
    @GetMapping("list")
    public String list(Model model) {
        model.addAttribute("list", memberService.findMembers());
        return "member/list";
    }

    // 등록 jsp
    @GetMapping(value = "add")
    public String add() {
        return "member/add";
    }

    // 수정 jsp
    @GetMapping(value = "mod/{id}")
    public String mod(@PathVariable Long id, Model model) {
        model.addAttribute("data", memberService.findOne(id));
        return "member/mod";
    }

}
