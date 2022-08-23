package com.witbackend.week8.repository;

import com.witbackend.week8.domain.MemberInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberInfoRepository extends JpaRepository<MemberInfo, Long> {
    @EntityGraph(attributePaths = "authorities")
    Optional<MemberInfo> findOneWithAuthoritiesByEmail(String email);
}
