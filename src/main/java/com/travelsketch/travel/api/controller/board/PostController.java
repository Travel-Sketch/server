package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.api.controller.board.request.UpdatePostRequest;
import com.travelsketch.travel.api.controller.board.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    /**
     * 게시물 등록 API
     *
     * @param request 등록할 게시물 정보
     * @return 등록된 게시물 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        CreatePostResponse response = CreatePostResponse.builder()
                .postId(1L)
                .title("게시물 제목")
                .createdDate(LocalDateTime.of(2023, 11, 29, 1, 30))
                .build();
        return ApiResponse.created(response);
    }

    /**
     * 게시물 목록 조회 API
     *
     * @return 게시물 목록
     */
    @GetMapping
    public ApiResponse<List<GetPostListResponse>> getPostList() {
        //searchPosts
        GetPostListResponse response1 = GetPostListResponse.builder()
                .postId(1L)
                .title("게시물 제목1")
                .createdDate(LocalDateTime.of(2023, 11, 30, 1, 30))
                .build();
        GetPostListResponse response2 = GetPostListResponse.builder()
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
    public ApiResponse<GetPostDetailResponse> getPostDetail(@PathVariable Long postId) {
        //searchPost
        GetPostDetailResponse response = GetPostDetailResponse.builder()
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
     * 게시물 수정 API
     *
     * @param request 게시물 수정 내용
     * @return 게시물 수정 내용
     */
    @PatchMapping("/{postId}")
    public ApiResponse<UpdatePostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostRequest request
    ) {
        //edit, modify(O)
        UpdatePostResponse response = UpdatePostResponse.builder()
                .postId(1L)
                .category("게시물 카테고리 수정")
                .title("게시물 제목 수정")
                .content("게시물 내용 수정")
                .lastModifiedDate(LocalDateTime.of(2023, 11, 30, 2, 00))
                .build();
        return ApiResponse.ok(response);
    }

    /**
     * 게시물 삭제 API
     *
     * @return 삭제된 게시물
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<DeletePostResponse> deletePost(@PathVariable Long postId) {
        //removePost
        DeletePostResponse response = DeletePostResponse.builder()
                .postId(1L)
                .title("게시물 제목 1")
                .isDeleted(true)
                .build();
        return ApiResponse.ok(response);
    }

}
