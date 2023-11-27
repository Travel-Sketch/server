package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.notice.request.CreateNoticeRequest;
import com.travelsketch.travel.api.controller.notice.request.ModifyNoticeRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NoticeControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/notices";

    @DisplayName("공지사항 등록시 제목을 필수값이다.")
    @Test
    void createNoticeWithoutTitle() throws Exception {
        //given
        CreateNoticeRequest request = CreateNoticeRequest.builder()
            .title(" ")
            .content("공지사항 내용입니다.")
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

    @DisplayName("공지사항 등록시 제목은 최대 50자이다.")
    @Test
    void createNoticeOutOfMaxSizeTitle() throws Exception {
        //given
        CreateNoticeRequest request = CreateNoticeRequest.builder()
            .title(getText(51))
            .content("공지사항 내용입니다.")
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

    @DisplayName("공지사항 등록시 내용은 필수값이다.")
    @Test
    void createNoticeWithoutContent() throws Exception {
        //given
        CreateNoticeRequest request = CreateNoticeRequest.builder()
            .title("공지사항 제목입니다.")
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

    @DisplayName("공지사항 수정시 제목은 필수값이다.")
    @Test
    void modifyNoticeWithoutTitle() throws Exception {
        //given
        ModifyNoticeRequest request = ModifyNoticeRequest.builder()
            .title(" ")
            .content("공지사항 내용입니다.")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/{noticeId}", 1L)
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

    @DisplayName("공지사항 수정시 제목은 최대 50자입니다.")
    @Test
    void modifyNoticeOutOfMaxSizeTitle() throws Exception {
        //given
        ModifyNoticeRequest request = ModifyNoticeRequest.builder()
            .title(getText(51))
            .content("공지사항 내용입니다.")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/{noticeId}", 1L)
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

    @DisplayName("공지사항 수정시 내용은 필수값이다.")
    @Test
    void modifyNoticeWithoutContent() throws Exception {
        //given
        ModifyNoticeRequest request = ModifyNoticeRequest.builder()
            .title("공지사항 제목입니다.")
            .content(" ")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/{noticeId}", 1L)
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