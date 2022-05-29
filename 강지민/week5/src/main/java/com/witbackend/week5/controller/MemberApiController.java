package com.witbackend.week5.controller;

import com.witbackend.week5.domain.Member;
import com.witbackend.week5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberApiController {
    private final MemberService memberService;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", memberService.findMembers());
        return "member/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "member/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(Member member) {
        memberService.register(member);
        return "redirect:/member/list";
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/member/list";
    }



}
