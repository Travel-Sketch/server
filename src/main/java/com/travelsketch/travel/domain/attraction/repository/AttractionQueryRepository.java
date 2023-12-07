package com.travelsketch.travel.domain.attraction.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.travelsketch.travel.domain.attraction.Attraction;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.travelsketch.travel.domain.attraction.QAttraction.attraction;

@Repository
public class AttractionQueryRepository {

    private final JPAQueryFactory queryFactory;

    public AttractionQueryRepository(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<Attraction> findByCond(int sidoId, int gugunId, int typeId, String query) {
        return queryFactory
            .select(attraction)
            .from(attraction)
            .where(
                attraction.sido.id.eq(sidoId),
                attraction.gugun.id.eq(gugunId),
                attraction.attractionTypeId.eq(typeId),
                attraction.title.like("%" + query + "%")
            )
            .orderBy(attraction.title.asc())
            .fetch();
    }
}
