package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreatePostResponse {

    private Long postId;
    private String title;
    private LocalDateTime createdDate;
    private List<UploadFile> files;

    @Builder
    private CreatePostResponse(Long postId, String title, LocalDateTime createdDate, List<UploadFile> files) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
        this.files = files;
    }

    public static CreatePostResponse of(Post post, List<UploadFile> files) {
        return CreatePostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .createdDate(post.getCreatedDate())
            .files(files)
            .build();
    }

}
