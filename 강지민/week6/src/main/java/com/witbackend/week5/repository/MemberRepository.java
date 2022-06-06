package com.witbackend.week5.repository;

import com.witbackend.week5.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
