package com.travelsketch.travel.api.controller.attraction.response;

import com.travelsketch.travel.domain.attraction.Gugun;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GugunResponse {

    private final Long gugunId;
    private final String name;

    @Builder
    private GugunResponse(Long gugunId, String name) {
        this.gugunId = gugunId;
        this.name = name;
    }

    public static GugunResponse of(Gugun gugun) {
        return GugunResponse.builder()
            .gugunId(gugun.getId())
            .name(gugun.getName())
            .build();
    }
}
