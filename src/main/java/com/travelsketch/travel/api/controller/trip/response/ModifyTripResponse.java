package com.travelsketch.travel.api.controller.trip.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyTripResponse {

    private final Long tripId;
    private final String title;
    private final int attractionCount;
    private final LocalDateTime modifiedDate;

    @Builder
    private ModifyTripResponse(Long tripId, String title, int attractionCount, LocalDateTime modifiedDate) {
        this.tripId = tripId;
        this.title = title;
        this.attractionCount = attractionCount;
        this.modifiedDate = modifiedDate;
    }
}
