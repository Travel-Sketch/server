package com.travelsketch.travel.docs.board;

import com.travelsketch.travel.api.controller.board.PostController;
import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.api.controller.board.response.ModifyPostResponse;
import com.travelsketch.travel.api.controller.board.response.RemovePostResponse;
import com.travelsketch.travel.api.service.board.FileStore;
import com.travelsketch.travel.api.service.board.PostService;
import com.travelsketch.travel.docs.RestDocsSupport;
import com.travelsketch.travel.security.SecurityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.multipart;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostControllerDocsTest extends RestDocsSupport {

    private final PostService postService = mock(PostService.class);
    private final SecurityUtils securityUtils = mock(SecurityUtils.class);
    private final FileStore fileStore = mock(FileStore.class);
    private static final String BASE_URL = "/api/v1/posts";

    @Override
    protected Object initController() {
        return new PostController(securityUtils, postService, fileStore);
    }

    @DisplayName("게시물 등록 API")
    @Test
    void createPost() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("cherry@naver.com");

        MockMultipartFile image1 = new MockMultipartFile(
            "files", //name
            "image1.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );
        MockMultipartFile image2 = new MockMultipartFile(
            "files", //name
            "image2.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        CreatePostResponse response = CreatePostResponse.builder()
            .postId(1L)
            .title("게시물 제목")
            .createdDate(LocalDateTime.of(2023, 12, 7, 10, 30))
            .uploadFileCount(2)
            .build();

        given(postService.createPost(anyString(), anyString(), anyString(), anyList()))
            .willReturn(response);

        mockMvc.perform(
                multipart(BASE_URL)
                    .file(image1)
                    .file(image2)
                    .part(new MockPart("title", "게시물 제목".getBytes()))
                    .part(new MockPart("content", "게시물 내용".getBytes()))
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("Authorization", "Bearer Access Token")
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
                requestParts(
                    partWithName("files").description("첨부파일").optional(),
                    partWithName("title").description("게시물 제목"),
                    partWithName("content").description("게시물 내용")
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
                        .description("게시물 등록 일시"),
                    fieldWithPath("data.uploadFileCount").type(JsonFieldType.NUMBER)
                        .description("업로드된 첨부파일 수")
                )
            ));
    }

    @DisplayName("게시물 수정 API")
    @Test
    void modifyPost() throws Exception {
        MockMultipartFile newImage1 = new MockMultipartFile(
            "newFiles", //name
            "image3.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );
        MockMultipartFile newImage2 = new MockMultipartFile(
            "newFiles", //name
            "image4.png", //originalFilename
            "image/png",
            "<<png data>>".getBytes()
        );

        ModifyPostResponse response = ModifyPostResponse.builder()
            .postId(1L)
            .title("게시물 제목")
            .content("게시물 내용")
            .lastModifiedDate(LocalDateTime.of(2023, 12, 7, 10, 30))
            .build();

        given(securityUtils.getCurrentEmail()).willReturn("test@test.com");
        given(postService.modifyPost(anyString(), anyLong(), anyString(), anyString(), anyList(), anyList()))
            .willReturn(response);

        mockMvc.perform(
                multipart(BASE_URL + "/{postId}", 1)
                    .part(new MockPart("title", "게시물 제목".getBytes()))
                    .part(new MockPart("content", "게시물 내용".getBytes()))
                    .file(newImage1)
                    .file(newImage2)
                    .part(new MockPart("deletedFileIds", "1".getBytes()))
                    .part(new MockPart("deletedFileIds", "2".getBytes()))
                    .with(request -> {
                        request.setMethod("PATCH");
                        return request;
                    })
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andDo(document("modify-post",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("postId").description("게시글id")
                ),
                requestParts(
                    partWithName("newFiles").description("새 첨부파일").optional(),
                    partWithName("deletedFileIds").description("삭제된 첨부파일 id").optional(),
                    partWithName("title").description("게시물 제목"),
                    partWithName("content").description("게시물 내용")
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
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("게시물 내용"),
                    fieldWithPath("data.lastModifiedDate").type(JsonFieldType.ARRAY)
                        .description("게시물 최근 수정 일시")
                )
            ));
    }

    @DisplayName("게시물 삭제 API")
    @Test
    void removePost() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("cherry@naver.com");

        RemovePostResponse response = RemovePostResponse.builder()
            .postId(1L)
            .title("질문 제목입니다.")
            .isDeleted(true)
            .build();

        given(postService.removePost(anyString(), anyLong()))
            .willReturn(response);

        mockMvc.perform(delete(BASE_URL + "/{postId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("remove-post",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("postId").description("게시글id")
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
                    fieldWithPath("data.isDeleted").type(JsonFieldType.BOOLEAN)
                        .description("게시물 삭제 여부")
                )

            ));
    }

}
