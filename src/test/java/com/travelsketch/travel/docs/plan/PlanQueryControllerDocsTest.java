package com.travelsketch.travel.docs.plan;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.plan.PlanQueryController;
import com.travelsketch.travel.api.controller.plan.response.PlanDetailResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import com.travelsketch.travel.api.service.plan.PlanQueryService;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
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

public class PlanQueryControllerDocsTest extends RestDocsSupport {

    private final PlanQueryService planQueryService = mock(PlanQueryService.class);
    private static final String BASE_URL = "/api/v1/plans";

    @Override
    protected Object initController() {
        return new PlanQueryController(planQueryService);
    }

    @DisplayName("여행 계획 목록 조회 API")
    @Test
    void searchPlans() throws Exception {
        PageRequest pageRequest = PageRequest.of(1, 10);

        PlanResponse response = PlanResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        PageImpl<PlanResponse> content = new PageImpl<>(List.of(response), pageRequest, 1);

        PageResponse<PlanResponse> result = new PageResponse<>(content);

        given(planQueryService.searchByCond(anyString(), any()))
            .willReturn(result);

        mockMvc.perform(
                get(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .queryParam("page", "1")
                    .queryParam("query", "여행")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-plans",
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
                    fieldWithPath("data.content[].planId").type(JsonFieldType.NUMBER)
                        .description("여행 계획 아이디"),
                    fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                        .description("여행 계획 제목"),
                    fieldWithPath("data.content[].writer").type(JsonFieldType.STRING)
                        .description("작성자 닉네임"),
                    fieldWithPath("data.content[].createdDate").type(JsonFieldType.ARRAY)
                        .description("여행 계획 등록 일시"),
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

    @DisplayName("여행 계획 상세 조회 API")
    @Test
    void searchPlan() throws Exception {
        PlanDetailResponse response = PlanDetailResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        given(planQueryService.searchPlan(anyLong()))
            .willReturn(response);

        mockMvc.perform(
                get(BASE_URL + "/{planId}", 1)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-plan",
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
                    fieldWithPath("data.writer").type(JsonFieldType.STRING)
                        .description("작성자"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("여행 계획 등록 일시")
                )
            ));
    }
}
