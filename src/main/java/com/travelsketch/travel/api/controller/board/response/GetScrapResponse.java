package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetScrapResponse {

    private Long postId;
    private String title;

    @Builder
    private GetScrapResponse(Long postId, String title) {
        this.postId = postId;
        this.title = title;
    }

}
