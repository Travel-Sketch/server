package com.travelsketch.travel.api.controller.qna.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAnswerRequest {

    private String answer;

    @Builder
    private CreateAnswerRequest(String answer) {
        this.answer = answer;
    }
}
