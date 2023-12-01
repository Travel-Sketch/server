package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {
    // String 객체를 반환하는 API에 responseFields를 설정하려고 하면
    // 'content as it could not be parsed as JSON or XML' 에러 발생해서 request 따로 만듦!
    private String content;

    @Builder
    private CreateCommentRequest(String content) {
        this.content = content;
    }

}
