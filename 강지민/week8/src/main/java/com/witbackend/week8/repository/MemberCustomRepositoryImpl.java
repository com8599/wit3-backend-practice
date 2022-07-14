package com.witbackend.week8.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.witbackend.week8.domain.Member;
import com.witbackend.week8.domain.SearchCondition;
import com.witbackend.week8.domain.SearchType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Supplier;

import static com.witbackend.week8.domain.QMember.member;

@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    public final JPAQueryFactory queryFactory;

    @Override
    public List<Member> search(SearchCondition condition, Pageable pageable) {
        return queryFactory
                .selectFrom(member)
                .where(isSearchable(condition.getType(), condition.getContent()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

    BooleanBuilder emailCt(String content) {
        return nullSafeBuilder(() -> member.email.contains(content));
    }

    BooleanBuilder passwordCt(String content) {
        return nullSafeBuilder(() -> member.password.contains(content));
    }

    BooleanBuilder isSearchable(SearchType sType, String content) {
        if (sType == SearchType.EM) {
            return emailCt(content);
        }
        else if (sType == SearchType.PD) {
            return passwordCt(content);
        }
        else {
            return emailCt(content).or(passwordCt(content));
        }
    }
}
