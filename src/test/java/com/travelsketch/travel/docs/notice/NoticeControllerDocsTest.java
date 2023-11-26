package com.travelsketch.travel.docs.notice;

import com.travelsketch.travel.api.controller.notice.NoticeController;
import com.travelsketch.travel.api.controller.notice.request.CreateNoticeRequest;
import com.travelsketch.travel.api.controller.notice.request.ModifyNoticeRequest;
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
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NoticeControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/notices";

    @Override
    protected Object initController() {
        return new NoticeController();
    }

    @DisplayName("공지사항 등록 API")
    @Test
    void createNotice() throws Exception {
        CreateNoticeRequest request = CreateNoticeRequest.builder()
            .title("공지사항 제목입니다.")
            .content("공지사항 내용입니다.")
            .build();

        mockMvc.perform(
                post(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-notice",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING)
                        .description("내용")
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
                    fieldWithPath("data.noticeId").type(JsonFieldType.NUMBER)
                        .description("공지사항 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("공지사항 제목"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("공지사항 등록 일시")
                )
            ));
    }

    @DisplayName("공지사항 수정 API")
    @Test
    void modifyNotice() throws Exception {
        ModifyNoticeRequest request = ModifyNoticeRequest.builder()
            .title("수정된 공지사항 제목입니다.")
            .content("수정된 공지사항 내용입니다.")
            .build();

        mockMvc.perform(
                patch(BASE_URL + "/{noticeId}", 1L)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-notice",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("noticeId")
                        .description("공지사항 아이디")
                ),
                requestFields(
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING)
                        .description("내용")
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
                    fieldWithPath("data.noticeId").type(JsonFieldType.NUMBER)
                        .description("공지사항 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("공지사항 제목"),
                    fieldWithPath("data.modifiedDate").type(JsonFieldType.ARRAY)
                        .description("공지사항 수정 일시")
                )
            ));
    }

    @DisplayName("공지사항 삭제 API")
    @Test
    void removeNotice() throws Exception {
        mockMvc.perform(
                delete(BASE_URL + "/{noticeId}", 1L)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-notice",
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("noticeId")
                        .description("공지사항 아이디")
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
                    fieldWithPath("data.noticeId").type(JsonFieldType.NUMBER)
                        .description("공지사항 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("공지사항 제목"),
                    fieldWithPath("data.removedDate").type(JsonFieldType.ARRAY)
                        .description("공지사항 삭제 일시")
                )
            ));
    }
}