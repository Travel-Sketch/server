package com.travelsketch.travel.domain.plan.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.travelsketch.travel.domain.member.QMember.member;
import static com.travelsketch.travel.domain.plan.QPlan.plan;

@Repository
public class PlanQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PlanQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PlanResponse> findByCond(String query, Pageable pageable) {
        List<Long> ids = queryFactory
            .select(plan.id)
            .from(plan)
            .where(
                plan.isDeleted.isFalse(),
                plan.title.like("%" + query + "%")
            )
            .orderBy(plan.createdDate.desc())
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
            .select(
                Projections.constructor(
                    PlanResponse.class,
                    plan.id,
                    plan.title,
                    plan.member.nickname,
                    plan.createdDate
                )
            )
            .from(plan)
            .join(plan.member, member)
            .where(plan.id.in(ids))
            .orderBy(plan.createdDate.desc())
            .fetch();
    }

    public int findCountByCond(String query) {
        return queryFactory
            .select(plan.id)
            .from(plan)
            .where(
                plan.isDeleted.isFalse(),
                plan.title.like("%" + query + "%")
            )
            .fetch()
            .size();
    }
}
