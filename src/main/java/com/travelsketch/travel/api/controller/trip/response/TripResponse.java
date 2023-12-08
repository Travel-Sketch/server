package com.travelsketch.travel.api.controller.trip.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TripResponse {

    private final Long tripId;
    private final String title;
    private final String writer;
    private final LocalDateTime createdDate;

    @Builder
    private TripResponse(Long tripId, String title, String writer, LocalDateTime createdDate) {
        this.tripId = tripId;
        this.title = title;
        this.writer = writer;
        this.createdDate = createdDate;
    }
}
