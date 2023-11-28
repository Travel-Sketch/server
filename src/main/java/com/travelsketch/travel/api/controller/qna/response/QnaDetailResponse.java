package com.travelsketch.travel.api.controller.qna.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QnaDetailResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final String content;
    private final String answer;
    private final LocalDateTime createdDate;

    @Builder
    private QnaDetailResponse(Long qnaId, String type, String title, String content, String answer, LocalDateTime createdDate) {
        this.qnaId = qnaId;
        this.type = type;
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.createdDate = createdDate;
    }
}
