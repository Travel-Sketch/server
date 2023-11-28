package com.travelsketch.travel.api.controller.qna.response;

import com.travelsketch.travel.domain.qna.Qna;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateQuestionResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final LocalDateTime createdDate;

    @Builder
    private CreateQuestionResponse(Long qnaId, String type, String title, LocalDateTime createdDate) {
        this.qnaId = qnaId;
        this.type = type;
        this.title = title;
        this.createdDate = createdDate;
    }

    public static CreateQuestionResponse of(Qna qna) {
        return CreateQuestionResponse.builder()
            .qnaId(qna.getId())
            .type(qna.getType().getText())
            .title(qna.getTitle())
            .createdDate(qna.getCreatedDate())
            .build();
    }
}
