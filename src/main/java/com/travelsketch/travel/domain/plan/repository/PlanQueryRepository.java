package com.travelsketch.travel.domain.plan.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PlanQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PlanResponse> findByCond(String query, Pageable pageable) {
        return null;
    }
}
