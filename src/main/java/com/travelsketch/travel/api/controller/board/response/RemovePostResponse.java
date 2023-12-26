package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemovePostResponse {

    private final Long postId;
    private final String title;
    private final Boolean isDeleted;

    @Builder
    private RemovePostResponse(Long postId, String title, Boolean isDeleted) {
        this.postId = postId;
        this.title = title;
        this.isDeleted = isDeleted;
    }

}
