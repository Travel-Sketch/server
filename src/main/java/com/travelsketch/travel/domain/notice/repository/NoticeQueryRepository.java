package com.travelsketch.travel.domain.notice.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.travelsketch.travel.domain.notice.QNotice.notice;

/**
 * 공지사항 조회용 저장소
 *
 * @author 임우택
 */
@Repository
public class NoticeQueryRepository {

    private final JPAQueryFactory queryFactory;

    public NoticeQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 검색 조건에 따라 공지사항 목록을 조회하여 반환한다.
     *
     * @param query    검색 조건
     * @param pageable 페이징 정보
     * @return 조회된 공지사항 정보
     */
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

    /**
     * 검색 조건에 일치하는 공지사항의 갯수를 카운팅하여 반환한다.
     *
     * @param query 검색 조건
     * @return 조회된 공지사항 갯수
     */
    public int findCountByCond(String query) {
        return queryFactory
            .select(notice.id)
            .from(notice)
            .where(
                notice.isDeleted.isFalse(),
                notice.title.like("%" + query + "%")
            )
            .fetch()
            .size();
    }

    /**
     * 아이디로 공지사항 단건을 조회하여 반환한다.
     *
     * @param noticeId 조회할 아이디
     * @return 조회된 공지사항
     */
    public Optional<NoticeDetailResponse> findById(Long noticeId) {
        NoticeDetailResponse content = queryFactory
            .select(
                Projections.constructor(
                    NoticeDetailResponse.class,
                    Expressions.asNumber(noticeId),
                    notice.title,
                    notice.content,
                    notice.isDeleted,
                    notice.createdDate
                )
            )
            .from(notice)
            .where(
                notice.id.eq(noticeId)
            )
            .fetchFirst();
        return Optional.ofNullable(content);
    }
}
