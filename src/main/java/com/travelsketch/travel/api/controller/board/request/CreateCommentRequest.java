package com.travelsketch.travel.api.controller.board.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCommentRequest {

    @NotBlank(message = "댓글 내용은 필수입니다.")
    private String content;

    @Builder
    private CreateCommentRequest(String content) {
        this.content = content;
    }

}
