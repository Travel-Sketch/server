package com.travelsketch.travel.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public boolean existEmail(String email) {
        return false;
    }
}
