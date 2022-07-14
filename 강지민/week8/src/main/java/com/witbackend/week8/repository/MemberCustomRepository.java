package com.witbackend.week8.repository;

import com.witbackend.week8.domain.Member;
import com.witbackend.week8.domain.SearchCondition;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface MemberCustomRepository {

    List<Member> search(SearchCondition searchCondition, Pageable pageable);
}
