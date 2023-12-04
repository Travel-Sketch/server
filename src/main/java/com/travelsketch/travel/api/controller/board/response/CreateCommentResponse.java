package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {

    private Long commentId;
    private String content;
    private LocalDateTime createdDate;

    @Builder
    private CreateCommentResponse(Long commentId, String content, LocalDateTime createdDate) {
        this.commentId = commentId;
        this.content = content;
        this.createdDate = createdDate;
    }

}
