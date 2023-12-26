package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyPostResponse {

    private final Long postId;
    private final String title;
    private final String content;
    private final LocalDateTime lastModifiedDate;

    @Builder
    private ModifyPostResponse(Long postId, String title, String content, LocalDateTime lastModifiedDate) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static ModifyPostResponse of(Post post) {
        return ModifyPostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .content(post.getContent())
            .lastModifiedDate(post.getLastModifiedDate())
            .build();
    }

}
