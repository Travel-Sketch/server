package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreateCommentRequest;
import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.api.controller.board.response.RemoveCommentResponse;
import com.travelsketch.travel.api.service.board.CommentService;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final SecurityUtils securityUtils;

    /**
     * 게시물 댓글 등록 API
     *
     * @param request 등록할 댓글 정보
     * @param postId  게시물 id
     * @return 등록된 댓글 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateCommentResponse> createComment(
        @Valid @RequestBody CreateCommentRequest request,
        @PathVariable Long postId
    ) {
        String email = securityUtils.getCurrentEmail();
        CreateCommentResponse response = commentService.createComment(
            email, postId, null, request.getContent()
        );

        return created(response);
    }

    /**
     * 대댓글 등록 API
     *
     * @param request 등록할 댓글 정보
     * @param postId 게시물 id
     * @param commentId 상위댓글 id
     * @return 등록된 댓글 정보
     */
    @PostMapping("/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateCommentResponse> createChildComment(
        @Valid @RequestBody CreateCommentRequest request,
        @PathVariable Long postId,
        @PathVariable Long commentId) {
        String email = securityUtils.getCurrentEmail();

        CreateCommentResponse response = commentService.createChildComment(
            email, postId, commentId, request.getContent()
        );

        return created(response);
    }

    /**
     * 게시물 댓글 삭제 API
     *
     * @param commentId 댓글 id
     * @param postId    게시글 id
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
        return ok(response);
    }

}
