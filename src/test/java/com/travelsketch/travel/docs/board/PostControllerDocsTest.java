package com.travelsketch.travel.docs.board;

import com.travelsketch.travel.api.controller.board.PostController;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/post";

    @Override
    protected Object initController() {
        return new PostController();
    }

    @DisplayName("게시물 등록 API")
    @Test
    void createPost() throws Exception {
        CreatePostRequest request = CreatePostRequest.builder()
                .title("post title")
                .content("post content")
                .build();

        mockMvc.perform(
                post(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-post",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("Bearer Access Token")
                        ),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING)
                                        .description("게시물 제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("게시물 내용")
                        ),
                        responseFields(
                                fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                fieldWithPath("data.postId").type(JsonFieldType.NUMBER)
                                        .description("게시물 아이디"),
                                fieldWithPath("data.title").type(JsonFieldType.STRING)
                                        .description("게시물 제목"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                                        .description("게시물 등록 일시")
                        )
                ));
    }
}
