package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RemovePostResponse {

    private final Long postId;
    private final String title;
    private final Boolean isDeleted;

    @Builder
    private RemovePostResponse(Long postId, String title, Boolean isDeleted) {
        this.postId = postId;
        this.title = title;
        this.isDeleted = isDeleted;
    }

    public static RemovePostResponse of(Post post) {
        return RemovePostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .isDeleted(post.getIsDeleted())
            .build();
    }

}
