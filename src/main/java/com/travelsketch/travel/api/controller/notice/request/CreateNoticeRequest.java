package com.travelsketch.travel.api.controller.notice.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateNoticeRequest {

    private String title;
    private String content;

    @Builder
    private CreateNoticeRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
