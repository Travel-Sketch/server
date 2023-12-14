package com.travelsketch.travel.domain.plan;

import com.travelsketch.travel.domain.TimeBaseEntity;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Plan extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttractionPlan> attractionPlans = new ArrayList<>();

    @Builder
    private Plan(String title, Member member) {
        this.title = title;
        this.member = member;
    }

    //== 연관관계 편의 메서드 ==//
    public static Plan createPlan(String title, Member member, List<Attraction> attractions) {
        Plan plan = Plan.builder()
            .title(title)
            .member(member)
            .build();

        for (Attraction attraction : attractions) {
            plan.addAttractionPlan(attraction);
        }

        return plan;
    }

    //== 연관관계 메서드 ==//
    private void addAttractionPlan(Attraction attraction) {
        AttractionPlan attractionPlan = AttractionPlan.builder()
            .plan(this)
            .attraction(attraction)
            .build();
        attractionPlans.add(attractionPlan);
    }

    //== 비즈니스 로직 ==//
    public Plan modifyPlan(String title, List<Attraction> attractions) {
        this.title = title;
        this.attractionPlans = new ArrayList<>();

        for (Attraction attraction : attractions) {
            this.addAttractionPlan(attraction);
        }

        return this;
    }
}
