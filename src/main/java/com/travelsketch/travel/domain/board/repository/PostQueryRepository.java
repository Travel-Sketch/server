package com.travelsketch.travel.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.domain.board.Post;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.travelsketch.travel.domain.board.QPost.post;
import static com.travelsketch.travel.domain.notice.QNotice.notice;

@Repository
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PostQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public Optional<Post> findByIdWithMember(Long postId) {
        Post content = queryFactory
            .select(post)
            .from(post)
            .leftJoin(post.member)
            .where(
                post.id.eq(postId)
            )
            .fetchFirst();
        return Optional.ofNullable(content);
    }

}
