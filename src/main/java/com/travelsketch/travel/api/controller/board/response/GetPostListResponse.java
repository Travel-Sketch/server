package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPostListResponse {

    private Long postId;
    private String title;
    private LocalDateTime createdDate;

    @Builder
    private GetPostListResponse(Long postId, String title, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
    }

}