package com.travelsketch.travel.docs.member;

import com.travelsketch.travel.api.controller.member.MemberQueryController;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.api.service.member.MemberQueryService;
import com.travelsketch.travel.docs.RestDocsSupport;
import com.travelsketch.travel.security.SecurityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberQueryControllerDocsTest extends RestDocsSupport {

    private final MemberQueryService memberQueryService = mock(MemberQueryService.class);
    private final SecurityUtils securityUtils = mock(SecurityUtils.class);
    private static final String BASE_URL = "/api/v1/members";

    @Override
    protected Object initController() {
        return new MemberQueryController(memberQueryService, securityUtils);
    }

    @DisplayName("회원 정보 조회 API")
    @Test
    @WithMockUser(username = "karina@naver.com")
    void searchMemberInfo() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        MemberInfo memberInfo = MemberInfo.builder()
            .email("karina@naver.com")
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .build();

        given(memberQueryService.searchMemberInfo(anyString()))
            .willReturn(memberInfo);

        mockMvc.perform(
                get(BASE_URL)
                    .header("Authorization", "Bearer Access Token")
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("search-member-info",
                getDocumentRequest(),
                getDocumentResponse(),
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
                    fieldWithPath("data.email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("data.birth").type(JsonFieldType.STRING)
                        .description("생년월일"),
                    fieldWithPath("data.gender").type(JsonFieldType.STRING)
                        .description("성별"),
                    fieldWithPath("data.nickname").type(JsonFieldType.STRING)
                        .description("닉네임")
                )
            ));
    }
}
