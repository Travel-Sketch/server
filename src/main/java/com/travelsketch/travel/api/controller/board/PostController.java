package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/post")
public class PostController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePostResponse> createPost(@RequestBody CreatePostRequest request) {
        CreatePostResponse response = CreatePostResponse.builder()
                .postId(1L)
                .title("게시물 제목")
                .createdDate(LocalDateTime.of(2023, 11, 29, 01, 30))
                .build();
        return ApiResponse.created(response);
    }

}
