package com.travelsketch.travel.api.controller.trip.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateTripResponse {

    private final Long tripId;
    private final String title;
    private final int attractionCount;
    private final LocalDateTime createdDate;

    @Builder
    private CreateTripResponse(Long tripId, String title, int attractionCount, LocalDateTime createdDate) {
        this.tripId = tripId;
        this.title = title;
        this.attractionCount = attractionCount;
        this.createdDate = createdDate;
    }
}
