package com.travelsketch.travel.domain.plan;

import com.travelsketch.travel.domain.TimeBaseEntity;
import com.travelsketch.travel.domain.attraction.Attraction;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttractionPlan extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attraction_plan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attraction_id")
    private Attraction attraction;

    @Builder
    private AttractionPlan(Plan plan, Attraction attraction) {
        this.plan = plan;
        this.attraction = attraction;
    }
}
