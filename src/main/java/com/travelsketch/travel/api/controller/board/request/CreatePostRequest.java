package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePostRequest {

    private String title;
    private String content;

    @Builder
    public CreatePostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
