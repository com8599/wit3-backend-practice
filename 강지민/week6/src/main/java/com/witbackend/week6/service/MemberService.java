package com.witbackend.week6.service;

import com.witbackend.week6.domain.Member;
import com.witbackend.week6.dto.MemberDTO.MemberRequestDTO;
import com.witbackend.week6.dto.MemberDTO.MemberResponseDTO;
import com.witbackend.week6.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // RequestDTO -> Entity로 변환
    public Long register(MemberRequestDTO memberRequestDTO) {
        Member member = memberRequestDTO.toEntity();
        memberRepository.save(member);
        return member.getId();
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
        Member member = memberRepository.findById(id).orElse(null);
        return new MemberResponseDTO(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
