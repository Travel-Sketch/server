package com.travelsketch.travel.api.controller.notice.response;

import com.travelsketch.travel.domain.notice.Notice;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateNoticeResponse {

    private final Long noticeId;
    private final String title;
    private final LocalDateTime createdDate;

    @Builder
    private CreateNoticeResponse(Long noticeId, String title, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.createdDate = createdDate;
    }

    public static CreateNoticeResponse of(Notice notice) {
        return CreateNoticeResponse.builder()
            .noticeId(notice.getId())
            .title(notice.getTitle())
            .createdDate(notice.getCreatedDate())
            .build();
    }
}
