package com.travelsketch.travel.api.controller.qna.request;

import com.travelsketch.travel.domain.qna.QnaType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateQuestionRequest {

    private QnaType type;
    private String title;
    private String content;
    private String pwd;

    @Builder
    private CreateQuestionRequest(QnaType type, String title, String content, String pwd) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.pwd = pwd;
    }
}
