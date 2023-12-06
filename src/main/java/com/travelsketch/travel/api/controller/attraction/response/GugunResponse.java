package com.travelsketch.travel.api.controller.attraction.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GugunResponse {

    private final Long gugunId;
    private final String name;

    @Builder
    public GugunResponse(Long gugunId, String name) {
        this.gugunId = gugunId;
        this.name = name;
    }
}
