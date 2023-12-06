package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
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

    // static 함수 : 인스턴트를 만들지 않고도 쓸 수 있는 함수
    public static CreatePostResponse of(Post post) {
        return CreatePostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .createdDate(post.getCreatedDate())
            .build();
    }

}
