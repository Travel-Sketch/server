package com.travelsketch.travel.api.controller.plan.response;

import com.travelsketch.travel.domain.plan.Plan;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePlanResponse {

    private final Long planId;
    private final String title;
    private final int attractionCount;
    private final LocalDateTime createdDate;

    @Builder
    private CreatePlanResponse(Long planId, String title, int attractionCount, LocalDateTime createdDate) {
        this.planId = planId;
        this.title = title;
        this.attractionCount = attractionCount;
        this.createdDate = createdDate;
    }

    public static CreatePlanResponse of(Plan plan) {
        return CreatePlanResponse.builder()
            .planId(plan.getId())
            .title(plan.getTitle())
            .attractionCount(plan.getAttractionPlans().size())
            .createdDate(plan.getCreatedDate())
            .build();
    }
}
