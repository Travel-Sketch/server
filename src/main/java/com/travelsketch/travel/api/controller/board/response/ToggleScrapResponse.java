package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ToggleScrapResponse {

    private Long postId;
    private boolean isDeleted;

    @Builder
    private ToggleScrapResponse(Long postId, boolean isDeleted) {
        this.postId = postId;
        this.isDeleted = isDeleted;
    }
}
