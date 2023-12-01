package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetPostDetailResponse {

    private Long postId;
    private String category;
    private String title;
    private String content;
    private Integer scrapCount;
    private Integer commentCount;
    private Boolean isDeleted;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    @Builder
    private GetPostDetailResponse(Long postId, String category, String title, String content, Integer scrapCount, Integer commentCount, Boolean isDeleted, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

}
