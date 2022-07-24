package com.example.demo.repository;

import com.example.demo.controller.dto.MemberResponseDto;
import com.example.demo.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //DB에 접근하는 모든 코드
    List<MemberResponseDto> findAllBy(Pageable pageable);
    @EntityGraph(attributePaths = "authorities")
    Optional<Member> findOneWithAuthoritiesByUsername(String username);
}
