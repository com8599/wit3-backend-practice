package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberApiController {
    @Autowired
    private final MemberService memberService;


    @GetMapping("")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberByMemberId(@PathVariable String id){
        return MemberService.getMemberByMemberId(id);
    }

    @PostMapping("")
    public Member registerMember(@RequestBody Member member){
        return MemberService.registerMember(member);
    }

    @PutMapping("/{id}")
    public void modifyMember(@PathVariable String id, @RequestBody Member Member) {
        MemberService.modifyMember(id, member);
    }

    @DeleteMapping("/{id}")
    public void removeMember(@PathVariable String id){
        memberService.removeMember(id);
    }



}
