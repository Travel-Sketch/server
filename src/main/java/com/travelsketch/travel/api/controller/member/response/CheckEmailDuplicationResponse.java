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

    public static CheckEmailDuplicationResponse of(boolean isUsed) {
        return CheckEmailDuplicationResponse.builder()
            .isUsed(isUsed)
            .build();
    }
}
