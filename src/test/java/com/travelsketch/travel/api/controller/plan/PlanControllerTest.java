package com.travelsketch.travel.api.controller.plan;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.plan.request.CreatePlanRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlanControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/plans";

    @DisplayName("계획 등록시 제목은 필수값이다.")
    @Test
    void createPlanWithoutTitle() throws Exception {
        //given
        CreatePlanRequest request = CreatePlanRequest.builder()
            .title(" ")
            .attractions(List.of(1, 2, 3))
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

    @DisplayName("계획 등록시 제목은 최대 50자이다.")
    @Test
    void createPlanOutOfMaxSizeTitle() throws Exception {
        //given
        CreatePlanRequest request = CreatePlanRequest.builder()
            .title(getText(51))
            .attractions(List.of(1, 2, 3))
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

    @DisplayName("계획 등록시 관광지 정보는 최소 1개 이상이다.")
    @Test
    void createPlanWithoutAttractions() throws Exception {
        //given
        CreatePlanRequest request = CreatePlanRequest.builder()
            .title("여행 계획 제목입니다.")
            .attractions(List.of())
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
            .andExpect(jsonPath("$.message").value("관광지 정보는 최소 1개 이상 입력해주세요."));
    }

    @DisplayName("계획 등록시 관광지 정보는 최대 10개이다.")
    @Test
    void createPlanOutOfMaxSizeAttractions() throws Exception {
        //given
        CreatePlanRequest request = CreatePlanRequest.builder()
            .title("여행 계획 제목입니다.")
            .attractions(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11))
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
            .andExpect(jsonPath("$.message").value("등록할 수 있는 관광지 수는 최대 10개입니다."));
    }

    private String getText(int size) {
        return "a".repeat(Math.max(0, size));
    }
}