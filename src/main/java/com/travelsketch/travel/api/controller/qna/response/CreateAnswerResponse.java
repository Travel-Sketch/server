package com.travelsketch.travel.api.controller.qna.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateAnswerResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final String answer;
    private final LocalDateTime modifiedDate;

    @Builder
    private CreateAnswerResponse(Long qnaId, String type, String title, String answer, LocalDateTime modifiedDate) {
        this.qnaId = qnaId;
        this.type = type;
        this.title = title;
        this.answer = answer;
        this.modifiedDate = modifiedDate;
    }
}
