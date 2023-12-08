package com.travelsketch.travel.api.controller.trip.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ModifyTripRequest {

    private String title;
    private List<Integer> attractions;

    @Builder
    private ModifyTripRequest(String title, List<Integer> attractions) {
        this.title = title;
        this.attractions = attractions;
    }
}
