package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeleteCommentResponse {

    private Long postId;
    private Long commentId;
    private String content;
    private Boolean isDeleted;

    @Builder
    private DeleteCommentResponse(Long postId, Long commentId, String content, Boolean isDeleted) {
        this.postId = postId;
        this.commentId = commentId;
        this.content = content;
        this.isDeleted = isDeleted;
    }

}
