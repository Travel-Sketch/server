package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchScrapsResponse {

    private Long postId;
    private String title;

    @Builder
    private SearchScrapsResponse(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }

}
