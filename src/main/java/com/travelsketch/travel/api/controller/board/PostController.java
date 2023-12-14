package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.api.controller.board.request.UpdatePostRequest;
import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.api.controller.board.response.ModifyPostResponse;
import com.travelsketch.travel.api.controller.board.response.RemovePostResponse;
import com.travelsketch.travel.api.service.board.FileStore;
import com.travelsketch.travel.api.service.board.PostService;
import com.travelsketch.travel.domain.board.UploadFile;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final SecurityUtils securityUtils;
    private final PostService postService;
    private final FileStore fileStore;

    /**
     * 게시물 등록 API
     *
     * @param request 등록할 게시물 정보
     * @return 등록된 게시물 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePostResponse> createPost(@Valid @ModelAttribute CreatePostRequest request) throws IOException {
        String email = securityUtils.getCurrentEmail();

        List<UploadFile> uploadFiles = fileStore.storeFiles(request.getFiles());

        CreatePostResponse response = postService.createPost(email, request.getTitle(), request.getContent(), uploadFiles);

        return created(response);
    }

    /**
     * 게시물 수정 API
     *
     * @param request 게시물 수정 내용
     * @return 게시물 수정 내용
     */
    @PatchMapping("/{postId}")
    public ApiResponse<ModifyPostResponse> modifyPost(
        @PathVariable Long postId,
        @RequestBody UpdatePostRequest request
    ) {
        ModifyPostResponse response = ModifyPostResponse.builder()
            .postId(1L)
            .category("게시물 카테고리 수정")
            .title("게시물 제목 수정")
            .content("게시물 내용 수정")
            .lastModifiedDate(LocalDateTime.of(2023, 11, 30, 2, 00))
            .build();
        return ok(response);
    }

    /**
     * 게시물 삭제 API
     *
     * @return 삭제된 게시물
     */
    @DeleteMapping("/{postId}")
    public ApiResponse<RemovePostResponse> removePost(@PathVariable Long postId) {
        RemovePostResponse response = RemovePostResponse.builder()
            .postId(1L)
            .title("게시물 제목 1")
            .isDeleted(true)
            .build();
        return ok(response);
    }

}
