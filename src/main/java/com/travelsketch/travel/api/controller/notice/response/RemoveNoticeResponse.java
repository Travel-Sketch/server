package com.travelsketch.travel.api.controller.notice.response;

import com.travelsketch.travel.domain.notice.Notice;
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

    public static RemoveNoticeResponse of(Notice notice) {
        return RemoveNoticeResponse.builder()
            .noticeId(notice.getId())
            .title(notice.getTitle())
            .removedDate(notice.getLastModifiedDate())
            .build();
    }
}
