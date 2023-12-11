package com.travelsketch.travel.api.controller.plan.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlanResponse {

    private final Long planId;
    private final String title;
    private final String writer;
    private final LocalDateTime createdDate;

    @Builder
    private PlanResponse(Long planId, String title, String writer, LocalDateTime createdDate) {
        this.planId = planId;
        this.title = title;
        this.writer = writer;
        this.createdDate = createdDate;
    }
}
