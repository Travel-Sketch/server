package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    private String title;
    private String content;

    @Builder
    private CreatePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
