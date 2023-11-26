package com.travelsketch.travel.api.controller.notice.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyNoticeResponse {

    private final Long noticeId;
    private final String title;
    private final LocalDateTime modifiedDate;

    @Builder
    private ModifyNoticeResponse(Long noticeId, String title, LocalDateTime modifiedDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.modifiedDate = modifiedDate;
    }
}
