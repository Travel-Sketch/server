package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UpdatePostResponse {

    private Long postId;
    private String category;
    private String title;
    private String content;
    private LocalDateTime lastModifiedDate;

    @Builder
    private UpdatePostResponse(Long postId, String category, String title, String content, LocalDateTime lastModifiedDate) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate;
    }

}
