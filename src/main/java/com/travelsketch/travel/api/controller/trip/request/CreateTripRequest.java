package com.travelsketch.travel.api.controller.trip.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateTripRequest {

    private String title;
    private List<Integer> attractions;

    @Builder
    private CreateTripRequest(String title, List<Integer> attractions) {
        this.title = title;
        this.attractions = attractions;
    }
}
