package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.api.controller.board.response.SearchScrapsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/{postId}/scraps")
public class ScrapQueryController {

    /**
     * 스크랩 게시물 목록 조회
     * @param postId 게시물 id
     * @param page 조회할 페이지 번호
     * @param query 조회할 검색 쿼리
     * @return 스크랩 게시물 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<SearchScrapsResponse>> searchScraps(
        @PathVariable Long postId,
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        if (isNegativeOrZero(page)) {
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        SearchScrapsResponse response1 = SearchScrapsResponse.builder()
            .postId(1L)
            .title("게시물 제목1")
            .build();
        SearchScrapsResponse response2 = SearchScrapsResponse.builder()
            .postId(2L)
            .title("게시물 제목2")
            .build();

        List<SearchScrapsResponse> scraps = List.of(response1, response2);
        PageImpl<SearchScrapsResponse> content = new PageImpl<>(scraps, pageRequest, 2);

        return ApiResponse.ok(new PageResponse<>(content));
    }

    /**
     * 입력 받은 숫자가 양수인 경우 true를 반환한다.
     *
     * @param number 검증 숫자
     * @return 양수인 경우 true 반환
     */
    private boolean isNegativeOrZero(int number) {
        return number <= 0;
    }

}
