package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.response.SearchScrapsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/{postId}/scraps")
public class ScrapQueryController {

    /**
     * 스크랩 게시물 목록 조회
     *
     * @return 스크랩 게시물 목록
     */
    @GetMapping
    public ApiResponse<List<SearchScrapsResponse>> searchScraps(@PathVariable Long postId) {
        SearchScrapsResponse response = SearchScrapsResponse.builder()
            .postId(1L)
            .title("게시물 제목1")
            .build();
        SearchScrapsResponse response2 = SearchScrapsResponse.builder()
            .postId(2L)
            .title("게시물 제목2")
            .build();
        return ApiResponse.ok(List.of(response, response2));
    }

}
