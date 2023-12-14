package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreatePostResponse {

    private final Long postId;
    private final String title;
    private final LocalDateTime createdDate;
    private final int uploadFileCount;

    @Builder
    private CreatePostResponse(Long postId, String title, LocalDateTime createdDate, int uploadFileCount) {
        this.postId = postId;
        this.title = title;
        this.createdDate = createdDate;
        this.uploadFileCount = uploadFileCount;
    }

    public static CreatePostResponse of(Post post, List<UploadFile> files) {
        return CreatePostResponse.builder()
            .postId(post.getId())
            .title(post.getTitle())
            .createdDate(post.getCreatedDate())
            .uploadFileCount(files.size())
            .build();
    }

}
