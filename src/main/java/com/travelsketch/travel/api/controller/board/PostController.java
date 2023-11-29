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
@RequestMapping("api/v1/posts")
public class PostController {

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


    @GetMapping
    public ApiResponse<List<GetPostListResponse>> getPostList() {
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

    @GetMapping("/{postId}")
    public  ApiResponse<GetPostDetailResponse> getPostDetail() {
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


    @PostMapping("/{postId}")
    public ApiResponse<UpdatePostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        UpdatePostResponse response = UpdatePostResponse.builder()
                .postId(1L)
                .category("게시물 카테고리 수정")
                .title("게시물 제목 수정")
                .content("게시물 내용 수정")
                .lastModifiedDate(LocalDateTime.of(2023, 11, 30, 2, 00))
                .build();
        return ApiResponse.ok(response);
    }


    @DeleteMapping("/{postId}")
    public ApiResponse<DeletePostResponse> deletePost() {
        DeletePostResponse response = DeletePostResponse.builder()
                .postId(1L)
                .title("게시물 제목 1")
                .isDeleted(true)
                .build();
        return ApiResponse.ok(response);
    }

}
