package com.witbackend.week8.repository;

import com.witbackend.week8.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
