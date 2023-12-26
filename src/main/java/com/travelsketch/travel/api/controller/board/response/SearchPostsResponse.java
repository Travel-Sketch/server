package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchPostsResponse {

    private final Long postId;
    private final String title;
    private final LocalDateTime createdDate;

    @Builder
    private SearchPostsResponse(Long postId, String title, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
    }

}
