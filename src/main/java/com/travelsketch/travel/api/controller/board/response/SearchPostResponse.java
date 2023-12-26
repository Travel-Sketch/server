package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SearchPostResponse {

    private final Long postId;
    private final String category;
    private final String title;
    private final String content;
    private final Integer scrapCount;
    private final Integer commentCount;
    private final Boolean isDeleted;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    @Builder
    private SearchPostResponse(Long postId, String category, String title, String content, Integer scrapCount, Integer commentCount, Boolean isDeleted, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
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
