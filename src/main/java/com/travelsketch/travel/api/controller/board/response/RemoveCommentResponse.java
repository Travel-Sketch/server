package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RemoveCommentResponse {

    private final Long postId;
    private final Long commentId;
    private final String content;
    private final Boolean isDeleted;

    @Builder
    private RemoveCommentResponse(Long postId, Long commentId, String content, Boolean isDeleted) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.isDeleted = isDeleted;
    }

}
