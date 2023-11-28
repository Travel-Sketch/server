package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CreatePostResponse {

    private Long postId;
    private String title;
    private LocalDateTime createdDate;

    @Builder
    public CreatePostResponse(Long postId, String title, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
    }

}
