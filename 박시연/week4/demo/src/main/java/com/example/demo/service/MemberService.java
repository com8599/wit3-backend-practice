package com.example.demo.service;

import com.example.demo.controller.dto.MemberResponseDto;
import com.example.demo.controller.dto.MemberSaveRequestDto;
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
    public Long save(MemberSaveRequestDto requestDto) {
        return memberRepository.save(requestDto.toEntity()).getId();
    }


    //read
    public MemberResponseDto readOne(Long id){
        Member entity = memberRepository.findById(id).orElse(null);
        return new MemberResponseDto(entity);
    }

    public List<Member> readAll() {
        List<Member> listMember= memberRepository.findAll();
        return listMember;
    }

    //update

    public Long update(Long id, MemberSaveRequestDto requestDto){
        Member member = memberRepository.findById(id).orElseThrow(null);
        member.update(requestDto.getId(), requestDto.getEmail());
        return id;
    }


    //delete

    public void delete(Long id){
        memberRepository.deleteById(id);
    }



}