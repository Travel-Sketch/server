package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostRequest {

    private String title;
    private String content;
    private String category;

    @Builder
    private CreatePostRequest(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
    }

}
