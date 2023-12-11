package com.travelsketch.travel.api.controller.plan.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RemovePlanResponse {

    private final Long planId;
    private final String title;
    private final LocalDateTime removedDate;

    @Builder
    private RemovePlanResponse(Long planId, String title, LocalDateTime removedDate) {
        this.planId = planId;
        this.title = title;
        this.removedDate = removedDate;
    }
}
