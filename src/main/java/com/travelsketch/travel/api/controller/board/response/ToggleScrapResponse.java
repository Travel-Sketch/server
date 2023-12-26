package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ToggleScrapResponse {

    private final Long postId;
    private final boolean isDeleted;

    @Builder
    private ToggleScrapResponse(Long postId, boolean isDeleted) {
        this.postId = postId;
        this.isDeleted = isDeleted;
    }
}
