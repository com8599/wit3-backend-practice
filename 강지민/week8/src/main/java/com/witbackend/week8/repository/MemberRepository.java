package com.witbackend.week8.repository;

import com.witbackend.week8.domain.Member;
import com.witbackend.week8.dto.MemberDto.MemberResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository {

    List<MemberResponseDto> findAllBy(Pageable pageable);
}
