package com.travelsketch.travel.api.controller.qna.response;

import com.travelsketch.travel.domain.qna.Qna;
import com.travelsketch.travel.domain.qna.QnaType;
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
    private final Boolean isDeleted;
    private final LocalDateTime createdDate;

    @Builder
    private QnaDetailResponse(Long qnaId, QnaType type, String title, String content, String answer, Boolean isDeleted, LocalDateTime createdDate) {
        this.qnaId = qnaId;
        this.type = type.getText();
        this.title = title;
        this.content = content;
        this.answer = answer;
        this.isDeleted = isDeleted;
        this.createdDate = createdDate;
    }

    public static QnaDetailResponse of(Qna qna) {
        return QnaDetailResponse.builder()
            .qnaId(qna.getId())
            .type(qna.getType())
            .title(qna.getTitle())
            .content(qna.getContent())
            .answer(qna.getAnswer())
            .isDeleted(qna.getIsDeleted())
            .createdDate(qna.getCreatedDate())
            .build();
    }
}
