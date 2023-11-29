package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreateCommentRequest;
import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.api.controller.board.response.DeleteCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/comments")
//@RequestMapping("api/v1/posts/{postId}/comments") 수정 예정!!
public class CommentController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    public ApiResponse<CreateCommentResponse> createComment(@RequestBody String request) {
    public ApiResponse<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request) {
        CreateCommentResponse response = CreateCommentResponse.builder()
                .commentId(1L)
                .content("게시물 댓글1")
                .createdDate(LocalDateTime.now())
                .build();
        return ApiResponse.created(response);
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<DeleteCommentResponse> deleteComment() {
        DeleteCommentResponse response = DeleteCommentResponse.builder()
                .postId(1L)
                .commentId(1L)
                .content("댓글 내용")
                .isDeleted(true)
                .build();
        return ApiResponse.ok(response);
    }

}
