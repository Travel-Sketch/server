package com.travelsketch.travel.api.controller.qna.response;

import com.travelsketch.travel.domain.qna.QnaType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static org.springframework.util.StringUtils.hasText;

@Getter
public class QnaResponse {

    private final Long qnaId;
    private final String type;
    private final String title;
    private final Boolean isLocked;
    private final Boolean isAnswer;
    private final LocalDateTime createdDate;

    @Builder
    public QnaResponse(Long qnaId, QnaType type, String title, String pwd, String answer, LocalDateTime createdDate) {
        this.qnaId = qnaId;
        this.type = type.getText();
        this.title = title;
        this.isLocked = hasText(pwd);
        this.isAnswer = hasText(answer);
        this.createdDate = createdDate;
    }
}
