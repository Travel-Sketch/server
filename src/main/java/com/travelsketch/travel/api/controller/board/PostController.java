package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.api.controller.board.request.ModifyPostRequest;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;
import static com.travelsketch.travel.api.controller.board.PostCustomValid.checkFileNameLength;

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
     * @throws IOException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePostResponse> createPost(@Valid @ModelAttribute CreatePostRequest request) throws IOException {
        checkFileNameLength(request.getFiles());

        String email = securityUtils.getCurrentEmail();

        List<UploadFile> uploadFiles = fileStore.storeFiles(request.getFiles());

        CreatePostResponse response = postService.createPost(email, request.getTitle(), request.getContent(), uploadFiles);

        return created(response);
    }

    /**
     * 게시물 수정 API
     * 
     * @param postId 게시물 id
     * @param request 게시물 수정 내용
     * @return 게시물 수정 내용
     * @throws IOException
     */
    @PatchMapping("/{postId}")
    public ApiResponse<ModifyPostResponse> modifyPost(
        @PathVariable Long postId,
        @Valid
        @ModelAttribute ModifyPostRequest request
    ) throws IOException {
        checkFileNameLength(request.getNewFiles());

        String email = securityUtils.getCurrentEmail();

        List<UploadFile> newFiles = fileStore.storeFiles(request.getNewFiles());

        ModifyPostResponse response = postService.modifyPost(email, postId, request.getTitle(), request.getContent(), newFiles, request.getDeletedFileIds());

        return ok(response);
    }
    
    /**
     * 게시물 삭제 API
     * 
     * @param postId 게시물 id
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
