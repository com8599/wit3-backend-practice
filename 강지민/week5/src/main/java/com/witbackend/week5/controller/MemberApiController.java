package com.witbackend.week5.controller;

import com.witbackend.week5.domain.Member;
import com.witbackend.week5.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/member")
public class MemberApiController {
    private final MemberService memberService;

    // 목록
    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("list", memberService.findMembers());
        return "member/list";
    }

    // 등록 jsp
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return "member/add";
    }
    
    // 등록 post 통신
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String postAdd(Member member) {
        memberService.register(member);
        return "redirect:/member/list";
    }

    // 수정 jsp
    @RequestMapping(value = "/mod/{id}", method = RequestMethod.GET)
    public String mod(@PathVariable Long id, Model model) {
        model.addAttribute("data", memberService.findOne(id));
        return "member/mod";
    }

    // 수정 post 통신
    @RequestMapping(value = "/mod/{id}", method = RequestMethod.POST)
    public String postMod(Member member) {
        memberService.register(member);
        return "redirect:/member/list";
    }

    // 삭제
    @RequestMapping("/del/{id}")
    public String del(@PathVariable Long id) {
        memberService.delete(id);
        return "redirect:/member/list";
    }

}
