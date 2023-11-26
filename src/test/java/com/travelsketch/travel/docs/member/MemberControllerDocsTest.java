package com.travelsketch.travel.docs.member;

import com.travelsketch.travel.api.controller.member.MemberController;
import com.travelsketch.travel.api.controller.member.request.ModifyNicknameRequest;
import com.travelsketch.travel.api.controller.member.request.ModifyPwdRequest;
import com.travelsketch.travel.api.controller.member.request.WithdrawalMemberRequest;
import com.travelsketch.travel.api.service.member.MemberService;
import com.travelsketch.travel.docs.RestDocsSupport;
import com.travelsketch.travel.security.SecurityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MemberControllerDocsTest extends RestDocsSupport {

    private final MemberService memberService = mock(MemberService.class);
    private final SecurityUtils securityUtils = mock(SecurityUtils.class);
    private static final String BASE_URL = "/api/v1/members";

    @Override
    protected Object initController() {
        return new MemberController(memberService, securityUtils);
    }

    @DisplayName("비밀번호 변경 API")
    @Test
    void modifyPwd() throws Exception {
        given(securityUtils.getCurrentEmail())
            .willReturn("karina@naver.com");

        ModifyPwdRequest request = ModifyPwdRequest.builder()
            .currentPwd("karina1234!")
            .newPwd("karina5678@")
            .build();

        given(memberService.modifyPwd(anyString(), anyString(), anyString()))
            .willReturn(true);

        mockMvc.perform(
                patch(BASE_URL + "/pwd")
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-pwd-member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("currentPwd").type(JsonFieldType.STRING)
                        .description("현재 비밀번호"),
                    fieldWithPath("newPwd").type(JsonFieldType.STRING)
                        .description("새로운 비밀번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.BOOLEAN)
                        .description("응답 데이터")
                )
            ));
    }

    @DisplayName("닉네임 변경 API")
    @Test
    void modifyNickname() throws Exception {
        ModifyNicknameRequest request  = ModifyNicknameRequest.builder()
            .nickname("에스파 카리나")
            .build();

        mockMvc.perform(
                patch(BASE_URL + "/nickname")
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("modify-nickname-member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("nickname").type(JsonFieldType.STRING)
                        .description("새로운 닉네임")
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
                    fieldWithPath("data.modifiedNickname").type(JsonFieldType.STRING)
                        .description("수정된 닉네임"),
                    fieldWithPath("data.modifiedDate").type(JsonFieldType.ARRAY)
                        .description("수정된 일시")
                )
            ));
    }

    @DisplayName("회원 탈퇴 API")
    @Test
    void withdrawal() throws Exception {
        WithdrawalMemberRequest request = WithdrawalMemberRequest.builder()
            .pwd("karina1234!")
            .build();

        mockMvc.perform(
                patch(BASE_URL + "/withdrawal")
                    .header("Authorization", "Bearer Access Token")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("withdrawal-member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                    headerWithName("Authorization")
                        .description("Bearer Access Token")
                ),
                requestFields(
                    fieldWithPath("pwd").type(JsonFieldType.STRING)
                        .description("현재 비밀번호")
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
                        .description("탈퇴한 이메일"),
                    fieldWithPath("data.name").type(JsonFieldType.STRING)
                        .description("탈퇴한 회원 이름"),
                    fieldWithPath("data.removedDate").type(JsonFieldType.ARRAY)
                        .description("탈퇴 일시")
                )
            ));
    }
}
