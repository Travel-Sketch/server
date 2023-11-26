package com.travelsketch.travel.domain.member.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.travelsketch.travel.domain.member.QMember.member;

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
        Long content = queryFactory
            .select(member.id)
            .from(member)
            .where(member.nickname.eq(nickname))
            .fetchFirst();

        return content != null;
    }

    public Optional<MemberInfo> findMemberInfoByEmail(String email) {
        return Optional.ofNullable(null);
    }
}
