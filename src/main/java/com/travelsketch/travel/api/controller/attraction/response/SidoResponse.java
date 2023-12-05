package com.travelsketch.travel.api.controller.attraction.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SidoResponse {

    private final Long sidoId;
    private final String name;

    @Builder
    public SidoResponse(Long sidoId, String name) {
        this.sidoId = sidoId;
        this.name = name;
    }
}
