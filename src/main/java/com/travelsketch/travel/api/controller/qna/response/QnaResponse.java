package com.travelsketch.travel.api.controller.qna.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QnaResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final Boolean isLocked;
    private final LocalDateTime createdDate;

    @Builder
    public QnaResponse(Long qnaId, String type, String title, Boolean isLocked, LocalDateTime createdDate) {
        this.qnaId = qnaId;
        this.type = type;
        this.title = title;
        this.isLocked = isLocked;
        this.createdDate = createdDate;
    }
}
