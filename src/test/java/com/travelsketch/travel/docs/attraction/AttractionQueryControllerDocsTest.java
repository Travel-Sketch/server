package com.travelsketch.travel.docs.attraction;

import com.travelsketch.travel.api.controller.attraction.AttractionQueryController;
import com.travelsketch.travel.api.controller.attraction.response.GugunResponse;
import com.travelsketch.travel.api.controller.attraction.response.SidoResponse;
import com.travelsketch.travel.api.service.attraction.AreaQueryService;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;

import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AttractionQueryControllerDocsTest extends RestDocsSupport {

    private final AreaQueryService areaQueryService = mock(AreaQueryService.class);
    private static final String BASE_URL = "/api/v1/sidos";

    @Override
    protected Object initController() {
        return new AttractionQueryController(areaQueryService);
    }

    @DisplayName("시도 목록 조회 API")
    @Test
    void searchSidos() throws Exception {
        SidoResponse response = SidoResponse.builder()
            .sidoId(1L)
            .name("서울")
            .build();

        given(areaQueryService.searchSidos())
            .willReturn(List.of(response));

        mockMvc.perform(
                get(BASE_URL)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-sidos",
                getDocumentResponse(),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].sidoId").type(JsonFieldType.NUMBER)
                        .description("시도 아이디"),
                    fieldWithPath("data[].name").type(JsonFieldType.STRING)
                        .description("시도 이름")
                )
            ));
    }

    @DisplayName("구군 목록 조회 API")
    @Test
    void searchGuguns() throws Exception {
        GugunResponse response = GugunResponse.builder()
            .gugunId(1L)
            .name("강남구")
            .build();

        given(areaQueryService.searchGuguns(anyLong()))
            .willReturn(List.of(response));

        mockMvc.perform(
                get(BASE_URL + "/{sidoId}/guguns", 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-guguns",
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("sidoId")
                        .description("시도 아이디")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].gugunId").type(JsonFieldType.NUMBER)
                        .description("구군 아이디"),
                    fieldWithPath("data[].name").type(JsonFieldType.STRING)
                        .description("구군 이름")
                )
            ));
    }

    @DisplayName("관광지 목록 조회 API")
    @Test
    void searchAttractions() throws Exception {
        mockMvc.perform(
                get(BASE_URL + "/{sidoId}/guguns/{gugunId}/attractions", 1L, 1L)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-attractions",
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("sidoId")
                        .description("시도 아이디"),
                    parameterWithName("gugunId")
                        .description("구군 아이디")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.ARRAY)
                        .description("응답 데이터"),
                    fieldWithPath("data[].attractionId").type(JsonFieldType.NUMBER)
                        .description("관광지 아이디"),
                    fieldWithPath("data[].title").type(JsonFieldType.STRING)
                        .description("관광지 제목"),
                    fieldWithPath("data[].zipcode").type(JsonFieldType.STRING)
                        .description("우편 번호"),
                    fieldWithPath("data[].address").type(JsonFieldType.STRING)
                        .description("주소"),
                    fieldWithPath("data[].tel").type(JsonFieldType.STRING)
                        .optional()
                        .description("관광지 연락처"),
                    fieldWithPath("data[].image").type(JsonFieldType.STRING)
                        .description("관광지 이미지"),
                    fieldWithPath("data[].longitude").type(JsonFieldType.NUMBER)
                        .description("경도"),
                    fieldWithPath("data[].latitude").type(JsonFieldType.NUMBER)
                        .description("위도")
                )
            ));
    }
}
