package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetScrapResponse {

    private Long postId;
    private String title;

    @Builder
    private GetScrapResponse(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }

}
