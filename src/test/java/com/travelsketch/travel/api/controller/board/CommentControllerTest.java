package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.board.request.CreateCommentRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CommentControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/posts/{postId}/comments";

    @DisplayName("게시물에 댓글을 등록할 수 있다.")
    @Test
    void createComment() throws Exception {
        //given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("댓글 내용")
            .build();

        mockMvc.perform(
                post(BASE_URL, 1)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"));
    }

    @DisplayName("입력 받은 댓글이 빈 문자열이면 예외가 발생한다.")
    @Test
    void createCommentWithNoContent() throws Exception {
        //given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content(" ")
            .build();

        mockMvc.perform(
                post(BASE_URL, 1)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("댓글 내용은 필수입니다."));
    }

    @DisplayName("게시물에 대댓글을 등록할 수 있다.")
    @Test
    void createChildComment() throws Exception {
        //given
        CreateCommentRequest request = CreateCommentRequest.builder()
            .content("댓글 내용")
            .build();

        mockMvc.perform(
                post(BASE_URL + "/{commentId}", 1, 1)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.code").value("201"));
    }
}