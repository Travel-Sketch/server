package com.travelsketch.travel.docs.board;

import com.travelsketch.travel.api.controller.board.CommentController;
import com.travelsketch.travel.api.controller.board.PostController;
import com.travelsketch.travel.api.controller.board.request.CreateCommentRequest;
import com.travelsketch.travel.api.controller.board.request.CreatePostRequest;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/posts/{postId}/comments";

    @Override
    protected Object initController() {
        return new CommentController();
    }

    @DisplayName("댓글 등록 API")
    @Test
    void createComment() throws Exception {
//        String request = "test1";
// https://lannstark.tistory.com/10
        CreateCommentRequest request = CreateCommentRequest.builder()
                .content("comment content 1")
                .build();

        mockMvc.perform(
                        post(BASE_URL, 1)
                                .header("Authorization", "Bearer Access Token")
//                                .contentType("text/plain")
                                .contentType(MediaType.APPLICATION_JSON)
                                .characterEncoding("utf-8")
//                                .content(request)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("create-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("postId").description("게시글id")
                        ),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("Bearer Access Token")
                        ),
                        requestFields(
                                fieldWithPath("content").type(JsonFieldType.STRING)
                                        .description("댓글 내용")
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
                                fieldWithPath("data.commentId").type(JsonFieldType.NUMBER)
                                        .description("댓글 아이디"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                                        .description("댓글 등록일시")
                        )
                ));
    }


    @DisplayName("댓글 삭제 API")
    @Test
    void deleteComment() throws Exception {

        mockMvc.perform(delete(BASE_URL + "/{commentId}", 1, 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer Access Token")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("delete-comment",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("postId").description("게시글id"),
                                parameterWithName("commentId").description("댓글id")
                        ),
                        requestHeaders(
                                headerWithName("Authorization")
                                        .description("Bearer Access Token")
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
                                fieldWithPath("data.commentId").type(JsonFieldType.NUMBER)
                                        .description("댓글 아이디"),
                                fieldWithPath("data.content").type(JsonFieldType.STRING)
                                        .description("댓글 내용"),
                                fieldWithPath("data.isDeleted").type(JsonFieldType.BOOLEAN)
                                        .description("댓글 삭제 여부")
                        )

                ));
    }

}
