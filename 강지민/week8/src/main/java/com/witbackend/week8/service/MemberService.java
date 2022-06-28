package com.witbackend.week8.service;

import com.witbackend.week8.domain.Member;
import com.witbackend.week8.dto.MemberDTO.MemberRequestDTO;
import com.witbackend.week8.dto.MemberDTO.MemberResponseDTO;
import com.witbackend.week8.dto.MemberDTO.MemberUpdateRequestDto;
import com.witbackend.week8.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // RequestDTO -> Entity로 변환
    public MemberResponseDTO register(MemberRequestDTO memberRequestDTO) {
        Member member = memberRequestDTO.toEntity();
        memberRepository.save(member);
        return new MemberResponseDTO(member);
    }

    // List<Member> -> List<MemberResponseDTO>로 변환
    public List<MemberResponseDTO> findMembers() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberResponseDTO> dtoList = new ArrayList<>();

        for (Member member : memberList) {
            dtoList.add(new MemberResponseDTO(member));
        }
        return dtoList;
        // return memberList.stream().map(MemberResponseDTO::new).collect(Collectors.toList());
    }

    public MemberResponseDTO findOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new MemberResponseDTO(member);
    }

    public MemberResponseDTO updateMember(Long id, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        member.update(memberUpdateRequestDto.getEmail(), memberUpdateRequestDto.getPassword());

        memberRepository.save(member);

        return new MemberResponseDTO(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
