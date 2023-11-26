package com.travelsketch.travel.api.controller.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NoticeResponse {

    private final Long noticeId;
    private final String title;
    private final LocalDateTime createdDate;

    @Builder
    public NoticeResponse(Long noticeId, String title, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.createdDate = createdDate;
    }
}
