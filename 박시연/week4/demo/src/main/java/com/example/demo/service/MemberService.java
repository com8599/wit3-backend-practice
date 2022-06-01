package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberService memberService;


    public static Member getMemberByMemberId(String id) {
        return MemberService.getMemberByMemberId(id);
    }

    public static Member registerMember(Member member) {
        return MemberService.registerMember(member);
    }

    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    public void removeMember(){
        return memberService.removeMember();
    }

    public void modifyMember() {
        MemberService.modifyMember();
    }
}
