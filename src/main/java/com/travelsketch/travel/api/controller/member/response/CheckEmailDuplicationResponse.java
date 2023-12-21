package com.travelsketch.travel.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CheckEmailDuplicationResponse {

    private final Boolean isUsed;

    @Builder
    private CheckEmailDuplicationResponse(Boolean isUsed) {
        this.isUsed = isUsed;
    }
}
