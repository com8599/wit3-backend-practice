package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    //비즈니스 로직에 관련하는 모든 코드는 여기에
    private final MemberRepository memberRepository;

    //create
    public void save(Member member) {
        memberRepository.save(member);
    }


    //read
    public void readOne(Long id){
        memberRepository.findById(id).orElse(null);
    }

    public List<Member> readAll() {
        memberRepository.findAll();
        return null;
    }


    //delete

    public void delete(Long id){
        memberRepository.deleteById(id);
    }



}