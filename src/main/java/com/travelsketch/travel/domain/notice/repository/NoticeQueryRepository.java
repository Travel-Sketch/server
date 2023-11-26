package com.travelsketch.travel.domain.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.travelsketch.travel.domain.notice.QNotice.notice;

@Repository
public class NoticeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<NoticeResponse> findByCond(String query, Pageable pageable) {
        List<Long> ids = queryFactory
            .select(notice.id)
            .from(notice)
            .where(
                notice.isDeleted.isFalse(),
                notice.title.like("%" + query + "%")
            )
            .orderBy(notice.createdDate.desc())
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        if (CollectionUtils.isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
            .select(
                Projections.constructor(
                    NoticeResponse.class,
                    notice.id,
                    notice.title,
                    notice.createdDate
                )
            )
            .from(notice)
            .where(
                notice.id.in(ids)
            )
            .orderBy(notice.createdDate.desc())
            .fetch();
    }

    public int findCountByCond(String query) {
        return 0;
    }

}
