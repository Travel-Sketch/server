package com.travelsketch.travel.api.controller.qna.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateQuestionRequest {

    private String type;
    private String title;
    private String content;
    private String pwd;

    @Builder
    private CreateQuestionRequest(String type, String title, String content, String pwd) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.pwd = pwd;
    }
}
