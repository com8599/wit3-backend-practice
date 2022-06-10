package com.witbackend.week6.repository;

import com.witbackend.week6.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
