package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemovePostResponse {

    private Long postId;
    private String title;
    private Boolean isDeleted;

    @Builder
    private RemovePostResponse(Long postId, String title, Boolean isDeleted) {
        this.postId = postId;
        this.title = title;
        this.isDeleted = isDeleted;
    }

}
