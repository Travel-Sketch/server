package com.travelsketch.travel.docs.qna;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.QnaQueryController;
import com.travelsketch.travel.api.controller.qna.response.QnaDetailResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.api.service.qna.QnaQueryService;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static com.travelsketch.travel.domain.qna.QnaType.ACCOUNT;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QnaQueryControllerDocsTest extends RestDocsSupport {

    private final QnaQueryService qnaQueryService = mock(QnaQueryService.class);
    private static final String BASE_URL = "/api/v1/qna";

    @Override
    protected Object initController() {
        return new QnaQueryController(qnaQueryService);
    }

    @DisplayName("QnA 목록 조회 API")
    @Test
    void searchQnas() throws Exception {
        QnaResponse response = QnaResponse.builder()
            .qnaId(1L)
            .type(ACCOUNT)
            .title("QnA 제목입니다.")
            .pwd("1234")
            .createdDate(LocalDateTime.of(2023, 11, 27, 19, 7))
            .build();

        PageRequest pageRequest = PageRequest.of(0, 10);

        given(qnaQueryService.searchQnas(anyString(), any()))
            .willReturn(new PageResponse<>(new PageImpl<>(List.of(response), pageRequest, 1)));

        mockMvc.perform(
                get(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .queryParam("page", "1")
                    .queryParam("query", "공지")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-qnas",
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
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
                        .description("조회된 QnA 목록"),
                    fieldWithPath("data.content[].qnaId").type(JsonFieldType.NUMBER)
                        .description("QnA 아이디"),
                    fieldWithPath("data.content[].type").type(JsonFieldType.STRING)
                        .description("질문 유형"),
                    fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                        .description("질문 제목"),
                    fieldWithPath("data.content[].isLocked").type(JsonFieldType.BOOLEAN)
                        .description("질문 잠금 여부"),
                    fieldWithPath("data.content[].isAnswer").type(JsonFieldType.BOOLEAN)
                        .description("답변 존재 여부"),
                    fieldWithPath("data.content[].createdDate").type(JsonFieldType.ARRAY)
                        .description("QnA 등록 일시"),
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

    @DisplayName("QnA 상세 조회 API")
    @Test
    void searchQna() throws Exception {
        QnaDetailResponse response  = QnaDetailResponse.builder()
            .qnaId(1L)
            .type(ACCOUNT)
            .title("QnA 제목입니다.")
            .content("QnA 내용입니다.")
            .answer("QnA 답변입니다.")
            .isDeleted(false)
            .createdDate(LocalDateTime.of(2023, 11, 27, 9, 27))
            .build();

        given(qnaQueryService.searchQna(anyLong(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                get(BASE_URL + "/{qnaId}", 1L)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-qna",
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("qnaId")
                        .description("QnA 아이디")
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
                    fieldWithPath("data.qnaId").type(JsonFieldType.NUMBER)
                        .description("QnA 아이디"),
                    fieldWithPath("data.type").type(JsonFieldType.STRING)
                        .description("질문 유형"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("QnA 제목"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("QnA 내용"),
                    fieldWithPath("data.answer").type(JsonFieldType.STRING)
                        .optional()
                        .description("QnA 답변"),
                    fieldWithPath("data.isDeleted").type(JsonFieldType.BOOLEAN)
                        .description("QnA 삭제 여부"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("QnA 등록 일시")
                )
            ));
    }
}
