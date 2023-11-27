package com.travelsketch.travel.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.travelsketch.travel.domain.member.QMember.*;

/**
 * 회원 조회용 저장소
 *
 * @author 임우택
 */
@Repository
public class MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    public MemberQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 이메일이 존재하는지 확인한다.
     *
     * @param email 중복 검사할 이메일
     * @return 이메일을 사용중이라면 true
     */
    public boolean existEmail(String email) {
        Long content = queryFactory
            .select(member.id)
            .from(member)
            .where(member.email.eq(email))
            .fetchFirst();

        return content != null;
    }

    /**
     * 닉네임이 존재하는지 확인한다.
     *
     * @param nickname 중복 검사할 닉네임
     * @return 닉네임을 사용중이라면 true
     */
    public boolean existNickname(String nickname) {
        Long content = queryFactory
            .select(member.id)
            .from(member)
            .where(member.nickname.eq(nickname))
            .fetchFirst();

        return content != null;
    }

    /**
     * 이메일로 회원 정보를 조회한다.
     *
     * @param email 조회할 이메일
     * @return 조회된 회원 정보
     */
    public Optional<MemberInfo> findMemberInfoByEmail(String email) {
        MemberInfo memberInfo = queryFactory
            .select(
                Projections.constructor(
                    MemberInfo.class,
                    member.email,
                    member.name,
                    member.birth,
                    member.gender,
                    member.nickname
                )
            )
            .from(member)
            .where(
                member.email.eq(email)
            )
            .fetchFirst();
        return Optional.ofNullable(memberInfo);
    }
}
