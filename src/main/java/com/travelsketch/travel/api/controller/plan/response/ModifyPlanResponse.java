package com.travelsketch.travel.api.controller.plan.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyPlanResponse {

    private final Long planId;
    private final String title;
    private final int attractionCount;
    private final LocalDateTime modifiedDate;

    @Builder
    private ModifyPlanResponse(Long planId, String title, int attractionCount, LocalDateTime modifiedDate) {
        this.planId = planId;
        this.title = title;
        this.attractionCount = attractionCount;
        this.modifiedDate = modifiedDate;
    }
}
