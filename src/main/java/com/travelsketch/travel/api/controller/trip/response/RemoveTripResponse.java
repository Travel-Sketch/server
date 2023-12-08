package com.travelsketch.travel.api.controller.trip.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RemoveTripResponse {

    private final Long tripId;
    private final String title;
    private final LocalDateTime removedDate;

    @Builder
    private RemoveTripResponse(Long tripId, String title, LocalDateTime removedDate) {
        this.tripId = tripId;
        this.title = title;
        this.removedDate = removedDate;
    }
}
