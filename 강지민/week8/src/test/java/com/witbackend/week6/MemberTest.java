package com.witbackend.week6;

import com.witbackend.week8.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() {
//        Member member = Member.builder()
//                        .id(3L)
//                                .email("asdfasdf")
//                                        .password("2455")
//                                                .build();
//
//        memberRepository.save(member);
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
