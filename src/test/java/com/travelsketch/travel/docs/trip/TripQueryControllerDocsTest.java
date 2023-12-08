package com.travelsketch.travel.docs.trip;

import com.travelsketch.travel.api.controller.trip.TripQueryController;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TripQueryControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/trips";

    @Override
    protected Object initController() {
        return new TripQueryController();
    }

    @DisplayName("여행 계획 목록 조회 API")
    @Test
    void searchTrips() throws Exception {
        mockMvc.perform(
                get(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .queryParam("page", "1")
                    .queryParam("query", "여행")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-trips",
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
                    fieldWithPath("data.content[].tripId").type(JsonFieldType.NUMBER)
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
}
