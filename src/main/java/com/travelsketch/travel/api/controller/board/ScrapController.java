package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.response.SearchScrapsResponse;
import com.travelsketch.travel.api.controller.board.response.ToggleScrapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/{postId}/scraps")
public class ScrapController {

    /**
     * 게시물 스크랩 설정 API
     *
     * @param postId 게시물 id
     * @return 스크랩 여부
     */
    @PostMapping
    public ApiResponse<ToggleScrapResponse> toggleScrap(@PathVariable Long postId) {
        ToggleScrapResponse response = ToggleScrapResponse.builder()
                .postId(1L)
                .isDeleted(false)
                .build();
        return ApiResponse.created(response);
    }

}
