package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostQueryController {

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
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        SearchPostsResponse response1 = SearchPostsResponse.builder()
            .postId(1L)
            .title("게시물 제목1")
            .createdDate(LocalDateTime.of(2023, 11, 30, 1, 30))
            .build();
        SearchPostsResponse response2 = SearchPostsResponse.builder()
            .postId(2L)
            .title("게시물 제목2")
            .createdDate(LocalDateTime.of(2023, 11, 30, 1, 40))
            .build();

        List<SearchPostsResponse> posts = List.of(response1, response2);
        PageImpl<SearchPostsResponse> content = new PageImpl<>(posts, pageRequest, 2);

        return ApiResponse.ok(new PageResponse<>(content));
    }

    /**
     * 게시물 상세 정보 조회 API
     *
     * @param postId 게시물 id
     * @return 게시물 상세 정보
     */
    @GetMapping("/{postId}")
    public ApiResponse<SearchPostResponse> searchPost(@PathVariable Long postId) {
        SearchPostResponse response = SearchPostResponse.builder()
            .postId(1L)
            .category("게시물 카테고리 1")
            .title("게시물 제목 1")
            .content("게시물 내용 1")
            .scrapCount(5)
            .commentCount(7)
            .isDeleted(false)
            .createdDate(LocalDateTime.of(2023, 11, 30, 1, 50))
            .lastModifiedDate(LocalDateTime.of(2023, 11, 30, 1, 55))
            .build();
        return ApiResponse.ok(response);
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
