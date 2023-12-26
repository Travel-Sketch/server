package com.travelsketch.travel.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.domain.board.Post;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.travelsketch.travel.domain.board.QPost.post;

@Repository
public class PostQueryRepository {

    private final JPAQueryFactory queryFactory;

    public PostQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<SearchPostsResponse> findByCriteria(PageRequest pageRequest, String query) {
        List<Long> postIds = queryFactory
            .select(post.id)
            .from(post)
            .where(
                post.isDeleted.isFalse(),
                post.title.like("%" + query + "%")
            )
            .orderBy(post.createdDate.desc())
            .limit(pageRequest.getPageSize())
            .offset(pageRequest.getOffset())
            .fetch();

        if (postIds.isEmpty()) {
            return new ArrayList<>();
        }

        return queryFactory
            .select(
                Projections.constructor(
                    SearchPostsResponse.class,
                    post.id,
                    post.title,
                    post.createdDate
                )
            )
            .from(post)
            .where(
                post.id.in(postIds)
            )
            .orderBy(post.createdDate.desc())
            .fetch();
    }

    public Optional<Post> findByIdWithMember(Long postId) {
        Post content = queryFactory
            .select(post)
            .from(post)
            .leftJoin(post.member).fetchJoin()
            .where(
                post.id.eq(postId)
            )
            .fetchFirst();
        return Optional.ofNullable(content);
    }

}
