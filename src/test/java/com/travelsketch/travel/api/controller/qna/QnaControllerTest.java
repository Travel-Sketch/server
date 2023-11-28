package com.travelsketch.travel.api.controller.qna;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.qna.request.CreateQuestionRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.travelsketch.travel.domain.qna.QnaType.ACCOUNT;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class QnaControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/qna";

    @DisplayName("질문 등록시 질문 유형은 필수값이다.")
    @Test
    void createQuestionWithoutType() throws Exception {
        //given
        CreateQuestionRequest request = CreateQuestionRequest.builder()
            .title("QnA 질문입니다.")
            .content("QnA 내용입니다.")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("질문 유형은 필수입니다."));
    }

    @DisplayName("질문 등록시 제목은 필수값이다.")
    @Test
    void createQuestionWithoutTitle() throws Exception {
        //given
        CreateQuestionRequest request = CreateQuestionRequest.builder()
            .type(ACCOUNT)
            .title(" ")
            .content("QnA 내용입니다.")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 필수입니다."));
    }

    @DisplayName("질문 등록시 제목은 최대 50자입니다.")
    @Test
    void createQuestionOutOfMaxSizeTitle() throws Exception {
        //given
        CreateQuestionRequest request = CreateQuestionRequest.builder()
            .type(ACCOUNT)
            .title(getText(51))
            .content("QnA 내용입니다.")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("제목은 최대 50자입니다."));
    }

    @DisplayName("질문 등록시 내용은 필수값이다.")
    @Test
    void createQuestionWithoutContent() throws Exception {
        //given
        CreateQuestionRequest request = CreateQuestionRequest.builder()
            .type(ACCOUNT)
            .title("QnA 제목입니다.")
            .content(" ")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL)
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("내용은 필수입니다."));
    }

    private String getText(int size) {
        return "a".repeat(Math.max(0, size));
    }
}