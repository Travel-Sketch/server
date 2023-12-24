package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyPostResponse {

    private Long postId;
    private String title;
    private String content;
    private LocalDateTime lastModifiedDate;

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
            .lastModifiedDate(post.getLastModifiedDate())
            .build();
    }

}
