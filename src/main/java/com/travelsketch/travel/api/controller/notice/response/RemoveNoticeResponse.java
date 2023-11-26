package com.travelsketch.travel.api.controller.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RemoveNoticeResponse {

    private final Long noticeId;
    private final String title;
    private final LocalDateTime removedDate;

    @Builder
    private RemoveNoticeResponse(Long noticeId, String title, LocalDateTime removedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.removedDate = removedDate;
    }
}
