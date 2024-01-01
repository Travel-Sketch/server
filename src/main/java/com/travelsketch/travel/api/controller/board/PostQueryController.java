package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.api.service.board.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /**
     * 게시물 목록 조회 API
     *
     * @param page  조회할 페이지 번호
     * @param query 조회할 검색 쿼리
     * @return 게시물 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<SearchPostsResponse>> searchPosts(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        if (isNegativeOrZero(page)) {
            throw new IllegalArgumentException("페이지 번호가 유효하지 않습니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<SearchPostsResponse> response = postQueryService.searchByCond(pageRequest, query);

        return ok(response);
    }

    /**
     * 게시물 상세 정보 조회 API
     *
     * @param postId 게시물 id
     * @return 게시물 상세 정보
     */
    @GetMapping("/{postId}")
    public ApiResponse<SearchPostResponse> searchPost(@PathVariable Long postId) {
        SearchPostResponse response = postQueryService.searchByPostId(postId);
        return ok(response);
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
