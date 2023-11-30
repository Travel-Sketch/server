package com.travelsketch.travel.domain.qna.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.travelsketch.travel.domain.qna.QQna.qna;
import static org.springframework.util.CollectionUtils.isEmpty;

@Repository
public class QnaQueryRepository {

    private final JPAQueryFactory queryFactory;

    public QnaQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<QnaResponse> findByCond(String query, Pageable pageable) {
        List<Long> ids = queryFactory
            .select(qna.id)
            .from(qna)
            .where(
                qna.isDeleted.isFalse(),
                qna.title.like("%" + query + "%")
            )
            .orderBy(qna.createdDate.desc())
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .fetch();

        if (isEmpty(ids)) {
            return new ArrayList<>();
        }

        return queryFactory
            .select(
                Projections.constructor(
                    QnaResponse.class,
                    qna.id,
                    qna.type,
                    qna.title,
                    qna.pwd,
                    qna.answer,
                    qna.createdDate
                )
            )
            .from(qna)
            .where(qna.id.in(ids))
            .orderBy(qna.createdDate.desc())
            .fetch();
    }
}
