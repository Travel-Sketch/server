package com.travelsketch.travel.docs.qna;

import com.travelsketch.travel.api.controller.qna.QnaController;
import com.travelsketch.travel.api.controller.qna.request.CreateAnswerRequest;
import com.travelsketch.travel.api.controller.qna.request.CreateQuestionRequest;
import com.travelsketch.travel.api.controller.qna.response.CreateAnswerResponse;
import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
import com.travelsketch.travel.api.controller.qna.response.RemoveQnaResponse;
import com.travelsketch.travel.api.service.qna.QnaService;
import com.travelsketch.travel.docs.RestDocsSupport;
import com.travelsketch.travel.domain.qna.QnaType;
import com.travelsketch.travel.security.SecurityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class QnaControllerDocsTest extends RestDocsSupport {

    private final QnaService qnaService = mock(QnaService.class);
    private final SecurityUtils securityUtils = mock(SecurityUtils.class);
    private static final String BASE_URL = "/api/v1/qna";

    @Override
    protected Object initController() {
        return new QnaController(qnaService, securityUtils);
    }

    @DisplayName("질문 등록 API")
    @Test
    void createQuestion() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        CreateQuestionRequest request = CreateQuestionRequest.builder()
            .type(QnaType.ACCOUNT)
            .title("질문 제목입니다.")
            .content("질문 내용입니다.")
            .pwd("1234")
            .build();

        CreateQuestionResponse response = CreateQuestionResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("질문 제목입니다.")
            .createdDate(LocalDateTime.of(2023, 11, 27, 16, 39))
            .build();

        given(qnaService.createQuestion(anyString(), any()))
            .willReturn(response);

        mockMvc.perform(
                post(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-question",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("type").type(JsonFieldType.STRING)
                        .description("질문 유형"),
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("content").type(JsonFieldType.STRING)
                        .description("내용"),
                    fieldWithPath("pwd").type(JsonFieldType.STRING)
                        .description("비밀번호")
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
                        .description("질문 제목"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("질문 등록 일시")
                )
            ));
    }

    @DisplayName("답변 등록 API")
    @Test
    void createAnswer() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        CreateAnswerRequest request = CreateAnswerRequest.builder()
            .answer("질문 답변입니다.")
            .build();

        CreateAnswerResponse response = CreateAnswerResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("질문 제목입니다.")
            .answer("질문 답변입니다.")
            .modifiedDate(LocalDateTime.of(2023, 11, 27, 17, 32))
            .build();

        given(qnaService.createAnswer(anyString(), anyLong(), anyString()))
            .willReturn(response);

        mockMvc.perform(
                post(BASE_URL + "/{qnaId}", 1L)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("create-answer",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("qnaId")
                        .description("QnA 아이디")
                ),
                requestFields(
                    fieldWithPath("answer").type(JsonFieldType.STRING)
                        .description("질문 답변")
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
                        .description("질문 제목"),
                    fieldWithPath("data.answer").type(JsonFieldType.STRING)
                        .description("질문 답변"),
                    fieldWithPath("data.modifiedDate").type(JsonFieldType.ARRAY)
                        .description("답변 등록 일시")
                )
            ));
    }

    @DisplayName("QnA 삭제 API")
    @Test
    void removeQna() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        RemoveQnaResponse response = RemoveQnaResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("질문 제목입니다.")
            .removedDate(LocalDateTime.of(2023, 11, 27, 19, 0))
            .build();

        given(qnaService.removeQna(anyString(), anyLong()))
            .willReturn(response);

        mockMvc.perform(
                delete(BASE_URL + "/{qnaId}", 1L)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("remove-qna",
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
                        .description("질문 제목"),
                    fieldWithPath("data.removedDate").type(JsonFieldType.ARRAY)
                        .description("삭제 일시")
                )
            ));
    }
}
