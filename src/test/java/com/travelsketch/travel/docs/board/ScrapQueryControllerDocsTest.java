package com.travelsketch.travel.docs.board;

import com.travelsketch.travel.api.controller.board.ScrapQueryController;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
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

public class ScrapQueryControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1/posts/{postId}/scraps";

    @Override
    protected Object initController() {
        return new ScrapQueryController();
    }

    @DisplayName("스크랩 목록 조회 API")
    @Test
    void searchScraps() throws Exception {

        mockMvc.perform(get(BASE_URL+"?page=1&query=검색어", 1)
                .header("Authorization", "Bearer Access Token")
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-scraps",
                getDocumentRequest(),
                getDocumentResponse(),
                queryParameters(
                    parameterWithName("page").description("현재 페이지").optional(),
                    parameterWithName("query").description("검색어").optional()
                ),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
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
                    fieldWithPath("data.content[].postId").type(JsonFieldType.NUMBER)
                        .description("게시물 아이디"),
                    fieldWithPath("data.content[].title").type(JsonFieldType.STRING)
                        .description("게시물 제목"),
                    fieldWithPath("data.currentPage").type(JsonFieldType.NUMBER)
                        .description("현재 페이지"),
                    fieldWithPath("data.size").type(JsonFieldType.NUMBER)
                        .description("한 페이지 당 게시물 개수"),
                    fieldWithPath("data.isFirst").type(JsonFieldType.BOOLEAN)
                        .description("첫번째 페이지 여부"),
                    fieldWithPath("data.isLast").type(JsonFieldType.BOOLEAN)
                        .description("마지막 페이지 여부")
                )

            ));
    }

}
