package com.travelsketch.travel.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.travelsketch.travel.domain.member.QMember.*;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public boolean existEmail(String email) {
        Long content = queryFactory
            .select(member.id)
            .from(member)
            .where(member.email.eq(email))
            .fetchFirst();

        return content != null;
    }

    public boolean existNickname(String nickname) {
        return false;
    }
}
