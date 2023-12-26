package com.travelsketch.travel.docs.board;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.PostQueryController;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.api.service.board.PostQueryService;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PostQueryControllerDocsTest extends RestDocsSupport {

    private final PostQueryService postQueryService = mock(PostQueryService.class);
    private static final String BASE_URL = "/api/v1/posts";

    @Override
    protected Object initController() {
        return new PostQueryController(postQueryService);
    }

    @DisplayName("게시물 목록 조회 API")
    @Test
    void searchPosts() throws Exception {

        SearchPostsResponse response1 = SearchPostsResponse.builder()
            .postId(1L)
            .title("게시물 제목")
            .createdDate(LocalDateTime.of(2023, 12, 7, 10, 30))
            .build();
        SearchPostsResponse response2 = SearchPostsResponse.builder()
            .postId(2L)
            .title("게시물 제목2")
            .createdDate(LocalDateTime.of(2023, 12, 27, 10, 40))
            .build();
        SearchPostsResponse response3 = SearchPostsResponse.builder()
            .postId(3L)
            .title("게시물 제목3")
            .createdDate(LocalDateTime.of(2023, 12, 27, 10, 40))
            .build();
        List<SearchPostsResponse> responses = List.of(response1, response2, response3);

        PageRequest pageRequest = PageRequest.of(0, 10);

        PageImpl<SearchPostsResponse> content = new PageImpl<>(responses, pageRequest, responses.size());

        given(postQueryService.searchByCriteria(any(), anyString()))
            .willReturn(new PageResponse<>(content));

        mockMvc.perform(get(BASE_URL + "?page=1&query=검색어")
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-posts",
                getDocumentRequest(),
                getDocumentResponse(),
                queryParameters(
                    parameterWithName("page").description("현재 페이지").optional(),
                    parameterWithName("query").description("검색어").optional()
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
                    fieldWithPath("data.content[].createdDate").type(JsonFieldType.ARRAY)
                        .description("게시물 등록 일시"),
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

    @DisplayName("게시물 상세 조회 API")
    @Test
    void searchPost() throws Exception {

        mockMvc.perform(get(BASE_URL + "/{postId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-post",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("postId").description("게시글id")
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
                    fieldWithPath("data.postId").type(JsonFieldType.NUMBER)
                        .description("게시물 아이디"),
                    fieldWithPath("data.category").type(JsonFieldType.STRING)
                        .description("게시물 카테고리"),
                    fieldWithPath("data.title").type(JsonFieldType.STRING)
                        .description("게시물 제목"),
                    fieldWithPath("data.content").type(JsonFieldType.STRING)
                        .description("게시물 내용"),
                    fieldWithPath("data.scrapCount").type(JsonFieldType.NUMBER)
                        .description("게시물 스크랩 수"),
                    fieldWithPath("data.commentCount").type(JsonFieldType.NUMBER)
                        .description("게시물 댓글 수"),
                    fieldWithPath("data.isDeleted").type(JsonFieldType.BOOLEAN)
                        .description("게시물 삭제 여부"),
                    fieldWithPath("data.createdDate").type(JsonFieldType.ARRAY)
                        .description("게시물 등록 일시"),
                    fieldWithPath("data.lastModifiedDate").type(JsonFieldType.ARRAY)
                        .description("게시물 최근 수정 일시")
                )

            ));
    }

}
