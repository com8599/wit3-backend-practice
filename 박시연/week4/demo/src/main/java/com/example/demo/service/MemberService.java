package com.example.demo.service;

import com.example.demo.controller.dto.MemberDto;
import com.example.demo.controller.dto.MemberResponseDto;
import com.example.demo.controller.dto.MemberSaveRequestDto;
import com.example.demo.controller.dto.MemberUpdateRequestDto;
import com.example.demo.domain.Authority;
import com.example.demo.domain.Member;
import com.example.demo.exception.DuplicateMemberException;
import com.example.demo.repository.MemberRepository;
import com.example.demo.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class MemberService {

    //비즈니스 로직에 관련하는 모든 코드는 여기에
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


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


    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //회원 가입
    @Transactional
    public MemberDto signup(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByUsername(memberDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .username(memberDto.getUsername())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return MemberDto.from(memberRepository.save(member));
    }

    @Transactional
    public MemberDto getUserWithAuthorities(String username) {
        return MemberDto.from(memberRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional
    public MemberDto getMyUserWithAuthorities() {
        return MemberDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }


}