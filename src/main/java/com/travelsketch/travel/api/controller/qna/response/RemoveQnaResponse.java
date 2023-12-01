package com.travelsketch.travel.api.controller.qna.response;

import com.travelsketch.travel.domain.qna.Qna;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RemoveQnaResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final LocalDateTime removedDate;

    @Builder
    private RemoveQnaResponse(Long qnaId, String type, String title, LocalDateTime removedDate) {
        this.qnaId = qnaId;
        this.type = type;
        this.title = title;
        this.removedDate = removedDate;
    }

    public static RemoveQnaResponse of(Qna qna) {
        return RemoveQnaResponse.builder()
            .qnaId(qna.getId())
            .type(qna.getType().getText())
            .title(qna.getTitle())
            .removedDate(qna.getLastModifiedDate())
            .build();
    }
}
