package com.travelsketch.travel.docs.notice;

import com.travelsketch.travel.api.controller.notice.NoticeQueryController;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NoticeQueryControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/notices";

    @Override
    protected Object initController() {
        return new NoticeQueryController();
    }

    @DisplayName("공지사항 목록 조회 API")
    @Test
    void searchNotices() throws Exception {
        mockMvc.perform(
                get(BASE_URL)
                    .param("page", "1")
                    .param("query", "공지")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-notices",
                getDocumentResponse(),
                queryParameters(
                    parameterWithName("page")
                        .description("페이지 번호"),
                    parameterWithName("query")
                        .optional()
                        .description("검색 내용")
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
                    fieldWithPath("data.content").type(JsonFieldType.ARRAY)
                        .description("조회된 공지사항 목록"),
                    fieldWithPath("data.content[].noticeId").type(JsonFieldType.NUMBER)
                        .description("공지사항 아이디"),
                    fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                        .description("공지사항 제목"),
                    fieldWithPath("data.content[].createdDate").type(JsonFieldType.ARRAY)
                        .description("공지사항 등록 일시"),
                    fieldWithPath("data.currentPage").type(JsonFieldType.NUMBER)
                        .description("현재 페이지 번호"),
                    fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                        .description("데이터 요청 갯수"),
                    fieldWithPath("data.isFirst").type(JsonFieldType.BOOLEAN)
                        .description("첫 페이지 여부"),
                    fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN)
                        .description("마지막 페이지 여부")
                )
            ));
    }

    @DisplayName("공지사항 상세 조회 API")
    @Test
    void searchNotice() throws Exception {
        mockMvc.perform(
                get(BASE_URL + "/{noticeId}", 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-notice",
                getDocumentResponse(),
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
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("공지사항 내용"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("공지사항 등록 일시")
                )
            ));
    }
}
