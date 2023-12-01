package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreateCommentRequest;
import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.api.controller.board.response.RemoveCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

    /**
     * 게시물 댓글 등록 API
     *
     * @param request 등록할 댓글 정보
     * @return 등록된 댓글 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateCommentResponse> createComment(@RequestBody CreateCommentRequest request, @PathVariable Long postId) {
        CreateCommentResponse response = CreateCommentResponse.builder()
            .commentId(1L)
            .content("게시물 댓글1")
            .createdDate(LocalDateTime.now())
            .build();
        return ApiResponse.created(response);
    }

    /**
     * 게시물 댓글 삭제 API
     *
     * @return 삭제된 댓글 내용
     */
    @DeleteMapping("/{commentId}")
    public ApiResponse<RemoveCommentResponse> removeComment(@PathVariable String commentId, @PathVariable Long postId) {
        RemoveCommentResponse response = RemoveCommentResponse.builder()
                .postId(1L)
                .commentId(1L)
                .content("댓글 내용")
                .isDeleted(true)
                .build();
        return ApiResponse.ok(response);
    }

}
