package com.travelsketch.travel.docs.trip;

import com.travelsketch.travel.api.controller.trip.TripController;
import com.travelsketch.travel.api.controller.trip.request.CreateTripRequest;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TripControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/trips";

    @Override
    protected Object initController() {
        return new TripController();
    }

    @DisplayName("여행 계획 등록 API")
    @Test
    void createTrip() throws Exception {
        CreateTripRequest request = CreateTripRequest.builder()
            .title("나의 여행 계획 제목")
            .attractions(List.of(111111, 111112, 111113))
            .build();

        mockMvc.perform(
                post(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-trip",
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
                    fieldWithPath("data.tripId").type(JsonFieldType.NUMBER)
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
    void modifyTrip() throws Exception {
        CreateTripRequest request = CreateTripRequest.builder()
            .title("수정된 여행 계획 제목")
            .attractions(List.of(111111, 111112, 111113))
            .build();

        mockMvc.perform(
                patch(BASE_URL + "/{tripId}", 1)
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-trip",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                pathParameters(
                    parameterWithName("tripId")
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
                    fieldWithPath("data.tripId").type(JsonFieldType.NUMBER)
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
}
