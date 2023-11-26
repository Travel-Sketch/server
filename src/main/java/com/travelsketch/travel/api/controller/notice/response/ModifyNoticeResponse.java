package com.travelsketch.travel.api.controller.notice.response;

import com.travelsketch.travel.domain.notice.Notice;
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

    public static ModifyNoticeResponse of(Notice notice) {
        return ModifyNoticeResponse.builder()
            .noticeId(notice.getId())
            .title(notice.getTitle())
            .modifiedDate(notice.getLastModifiedDate())
            .build();
    }
}
