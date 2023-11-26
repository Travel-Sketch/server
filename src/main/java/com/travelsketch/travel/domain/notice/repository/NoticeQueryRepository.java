package com.travelsketch.travel.domain.notice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<NoticeResponse> findByCond(String query, Pageable pageable) {
        return new ArrayList<>();
    }

}
