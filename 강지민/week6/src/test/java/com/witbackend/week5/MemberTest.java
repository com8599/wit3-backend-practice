package com.witbackend.week5;

import com.witbackend.week5.domain.Member;
import com.witbackend.week5.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() {
        Member member = new Member();
        member.setId(4L);
        member.setEmail("jimin112688@hanmail.net");
        member.setPassword("simon1126");
        memberRepository.save(member);
    }

    @Test
    public void readAll() {
        memberRepository.findAll();
    }

    @Test
    public void readOne() {
        memberRepository.findById(3L);
    }

    @Test
    public void delete() {
        memberRepository.deleteById(2L);
    }
}
