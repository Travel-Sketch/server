package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveCommentResponse {

    private Long postId;
    private Long commentId;
    private String content;
    private Boolean isDeleted;

    @Builder
    private RemoveCommentResponse(Long postId, Long commentId, String content, Boolean isDeleted) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.isDeleted = isDeleted;
    }

}
