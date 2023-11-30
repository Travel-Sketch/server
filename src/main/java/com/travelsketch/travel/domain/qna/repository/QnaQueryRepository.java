package com.travelsketch.travel.domain.qna.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QnaQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QnaQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<QnaResponse> findByCond(String query, Pageable pageable) {
        return new ArrayList<>();
    }
}
