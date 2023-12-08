package com.travelsketch.travel.api.controller.plan.response;

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
}
