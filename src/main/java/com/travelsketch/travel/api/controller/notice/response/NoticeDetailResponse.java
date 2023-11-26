package com.travelsketch.travel.api.controller.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeDetailResponse {

    private final Long noticeId;
    private final String title;
    private final String content;
    private final Boolean isDeleted;
    private final LocalDateTime createdDate;

    @Builder
    public NoticeDetailResponse(Long noticeId, String title, String content, Boolean isDeleted, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
    }
}
