package com.example.demo.service;

import com.example.demo.controller.dto.MemberResponseDto;
import com.example.demo.controller.dto.MemberSaveRequestDto;
import com.example.demo.controller.dto.MemberUpdateRequestDto;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class MemberService {

    //비즈니스 로직에 관련하는 모든 코드는 여기에
    private final MemberRepository memberRepository;

    //create
    public MemberResponseDto save(MemberSaveRequestDto requestDto) {
    Member member = MemberSaveRequestDto.toEntity();
    memberRepository.save(member);
    return new MemberResponseDto(member);
    }


    //read
    public MemberResponseDto readOne(Long id){
        Member entity = memberRepository.findById(id).orElse(null);
        return new MemberResponseDto(entity);
    }

    public List<MemberResponseDto> readAllMembers(int page, Pageable pageable) {
        List<MemberResponseDto> listMember= memberRepository.findAllBy(PageRequest.of(page, 5));
        return listMember;
    }


    //update

    public Long update(Long id, MemberUpdateRequestDto requestDto){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("일치하는 id가 없습니다"));
        member.update(requestDto.getEmail(), requestDto.getPassword());
        return id;
    }


    //delete

    public void delete(Long id){
        memberRepository.deleteById(id);
    }



    public List<MemberResponseDto> findMembersByPageRequest(Pageable pageable){
        PageRequest pageRequest = PageRequest.of(0,3);
        List<Member> members = (List<Member>) memberRepository.findAll(pageRequest);

        List<MemberResponseDto> result = new ArrayList<>();
        for(Member member : members){
            result.add(new MemberResponseDto(member));
        }
        return memberRepository.findAllBy(pageable);
    }


}