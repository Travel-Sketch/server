package com.travelsketch.travel.api.controller.board.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeletePostResponse {

    private Long postId;
    private String title;
    private Boolean isDeleted;

    @Builder
    private DeletePostResponse(Long postId, String title, Boolean isDeleted) {
        this.postId = postId;
        this.title = title;
        this.isDeleted = isDeleted;
    }

}
