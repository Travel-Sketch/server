package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostQueryController {

    /**
     * 게시물 목록 조회 API
     *
     * @return 게시물 목록
     */
    @GetMapping
    public ApiResponse<List<SearchPostsResponse>> searchPosts() {
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
        return ApiResponse.ok(List.of(response1, response2));
    }

    /**
     * 게시물 상세 정보 조회 API
     *
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

}
