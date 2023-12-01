package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostResponse {

    private Long postId;
    private String title;
    private LocalDateTime createdDate;

    @Builder
    private CreatePostResponse(Long postId, String title, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
    }

}
