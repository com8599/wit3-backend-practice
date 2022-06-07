package com.witbackend.week6.service;

import com.witbackend.week6.domain.Member;
import com.witbackend.week6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long register(Member member) {
        return memberRepository.save(member).getId();
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(null);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

}
