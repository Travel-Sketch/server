package com.travelsketch.travel.api.controller.qna.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAnswerRequest {

    @NotBlank(message = "답변은 필수입니다.")
    private String answer;

    @Builder
    private CreateAnswerRequest(String answer) {
        this.answer = answer;
    }
}
