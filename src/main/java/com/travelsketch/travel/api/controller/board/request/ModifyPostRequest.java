package com.travelsketch.travel.api.controller.board.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPostRequest {

    private String category;
    private String title;
    private String content;

    @Builder
    private ModifyPostRequest(String category, String title, String content) {
        this.category = category;
        this.title = title;
        this.content = content;
    }

}
