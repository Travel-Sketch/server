package com.travelsketch.travel.api.controller.attraction.response;

import com.travelsketch.travel.domain.attraction.Sido;
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

    public static SidoResponse of(Sido sido) {
        return SidoResponse.builder()
            .sidoId(sido.getId())
            .name(sido.getName())
            .build();
    }
}
