package com.witbackend.week5.service;

import com.witbackend.week5.domain.Member;
import com.witbackend.week5.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(Member member) {
        memberRepository.save(member);
    }

    public List<Member> findMembers() {
        List<Member> list = memberRepository.findAll();
        return list;
    }

    public Member findOne(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.get();
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
