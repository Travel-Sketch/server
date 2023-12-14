package com.travelsketch.travel.docs.plan;

import com.travelsketch.travel.api.controller.plan.PlanController;
import com.travelsketch.travel.api.controller.plan.request.CreatePlanRequest;
import com.travelsketch.travel.api.controller.plan.request.ModifyPlanRequest;
import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.api.controller.plan.response.ModifyPlanResponse;
import com.travelsketch.travel.api.controller.plan.response.RemovePlanResponse;
import com.travelsketch.travel.api.service.plan.PlanService;
import com.travelsketch.travel.docs.RestDocsSupport;
import com.travelsketch.travel.security.SecurityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PlanControllerDocsTest extends RestDocsSupport {

    private final PlanService planService = mock(PlanService.class);
    private final SecurityUtils securityUtils = mock(SecurityUtils.class);
    private static final String BASE_URL = "/api/v1/plans";

    @Override
    protected Object initController() {
        return new PlanController(planService, securityUtils);
    }

    @DisplayName("여행 계획 등록 API")
    @Test
    void createPlan() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        CreatePlanRequest request = CreatePlanRequest.builder()
            .title("나의 여행 계획 제목")
            .attractions(List.of(111111, 111112, 111113))
            .build();

        CreatePlanResponse response = CreatePlanResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .attractionCount(3)
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        given(planService.createPlan(anyString(), anyString(), anyList()))
            .willReturn(response);

        mockMvc.perform(
                post(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-plan",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("attractions").type(JsonFieldType.ARRAY)
                        .description("등록할 관광지 아이디")
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
                    fieldWithPath("data.planId").type(JsonFieldType.NUMBER)
                        .description("여행 계획 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("여행 계획 제목"),
                    fieldWithPath("data.attractionCount").type(JsonFieldType.NUMBER)
                        .description("등록한 관광지 수"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("여행 계획 등록 일시")
                )
            ));
    }

    @DisplayName("여행 계획 수정 API")
    @Test
    void modifyPlan() throws Exception {
        ModifyPlanRequest request = ModifyPlanRequest.builder()
            .title("수정된 여행 계획 제목")
            .attractions(List.of(111111, 111112, 111113))
            .build();

        ModifyPlanResponse response = ModifyPlanResponse.builder()
            .planId(1L)
            .title("수정된 여행 계획 제목")
            .attractionCount(3)
            .modifiedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        given(planService.modifyPlan(anyLong(), anyString(), anyList()))
            .willReturn(response);

        mockMvc.perform(
                patch(BASE_URL + "/{planId}", 1)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-plan",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("planId")
                        .description("여행 계획 아이디")
                ),
                requestFields(
                    fieldWithPath("title").type(JsonFieldType.STRING)
                        .description("제목"),
                    fieldWithPath("attractions").type(JsonFieldType.ARRAY)
                        .description("등록할 관광지 아이디")
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
                    fieldWithPath("data.planId").type(JsonFieldType.NUMBER)
                        .description("여행 계획 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("여행 계획 제목"),
                    fieldWithPath("data.attractionCount").type(JsonFieldType.NUMBER)
                        .description("등록한 관광지 수"),
                    fieldWithPath("data.modifiedDate").type(JsonFieldType.ARRAY)
                        .description("여행 계획 등록 일시")
                )
            ));
    }

    @DisplayName("여행 계획 삭제 API")
    @Test
    void removePlan() throws Exception {
        RemovePlanResponse response = RemovePlanResponse.builder()
            .planId(1L)
            .title("삭제된 여행 계획 제목")
            .removedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        given(planService.removePlan(anyLong()))
            .willReturn(response);

        mockMvc.perform(
                delete(BASE_URL + "/{planId}", 1)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("remove-plan",
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("planId")
                        .description("여행 계획 아이디")
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
                    fieldWithPath("data.planId").type(JsonFieldType.NUMBER)
                        .description("여행 계획 아이디"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("여행 계획 제목"),
                    fieldWithPath("data.removedDate").type(JsonFieldType.ARRAY)
                        .description("여행 계획 등록 일시")
                )
            ));
    }
}
