package com.witbackend.week8.service;

import com.witbackend.week8.domain.Member;
import com.witbackend.week8.domain.SearchCondition;
import com.witbackend.week8.dto.MemberDto.MemberRequestDto;
import com.witbackend.week8.dto.MemberDto.MemberResponseDto;
import com.witbackend.week8.dto.MemberDto.MemberUpdateRequestDto;
import com.witbackend.week8.repository.MemberCustomRepository;
import com.witbackend.week8.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberCustomRepository memberCustomRepository;

    // RequestDTO -> Entity로 변환
    public MemberResponseDto register(MemberRequestDto memberRequestDTO) {
        Member member = memberRequestDTO.toEntity();
        memberRepository.save(member);
        return new MemberResponseDto(member);
    }

    // List<Member> -> List<MemberResponseDTO>로 변환
    public List<MemberResponseDto> findMembers(int page, Pageable pageable) {
        return memberRepository.findAllBy(PageRequest.of(page, 3));
    }

    // 검색 포함 목록보기
    public List<MemberResponseDto> findSearchMembers(int page, Pageable pageable, SearchCondition condition) {
        List<Member> search = memberCustomRepository.search(condition, PageRequest.of(page, 3));
        return search.stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    public MemberResponseDto findOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
        return new MemberResponseDto(member);
    }

    public MemberResponseDto updateMember(Long id, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        member.update(memberUpdateRequestDto.getEmail(), memberUpdateRequestDto.getPassword());

        memberRepository.save(member);

        return new MemberResponseDto(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }
}
