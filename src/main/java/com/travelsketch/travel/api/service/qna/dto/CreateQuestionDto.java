package com.travelsketch.travel.api.service.qna.dto;

import com.travelsketch.travel.domain.qna.QnaType;
import lombok.Builder;

public record CreateQuestionDto(QnaType type, String title, String content, String pwd) {

    @Builder
    public CreateQuestionDto {
    }
}
