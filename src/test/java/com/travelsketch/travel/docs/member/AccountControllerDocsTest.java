package com.travelsketch.travel.docs.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.travelsketch.travel.api.controller.member.AccountController;
import com.travelsketch.travel.api.controller.member.request.AuthenticationNumberRequest;
import com.travelsketch.travel.api.controller.member.request.CheckAuthenticationNumberRequest;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
import com.travelsketch.travel.api.controller.member.request.LoginMemberRequest;
import com.travelsketch.travel.docs.RestDocsSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentRequest;
import static com.travelsketch.travel.docs.ApiDocumentUtil.getDocumentResponse;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerDocsTest extends RestDocsSupport {

    private static final String BASE_URL = "/api/v1";

    @Override
    protected Object initController() {
        return new AccountController();
    }

    @DisplayName("회원 가입 API")
    @Test
    void join() throws Exception {
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("temp@naver.com")
            .pwd("temp1234!")
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .build();

        mockMvc.perform(
                post(BASE_URL + "/join")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isCreated())
            .andDo(document("create-member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("pwd").type(JsonFieldType.STRING)
                        .description("비밀번호"),
                    fieldWithPath("name").type(JsonFieldType.STRING)
                        .description("이름"),
                    fieldWithPath("birth").type(JsonFieldType.STRING)
                        .description("생년월일"),
                    fieldWithPath("gender").type(JsonFieldType.STRING)
                        .description("성별"),
                    fieldWithPath("nickname").type(JsonFieldType.STRING)
                        .description("닉네임")
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
                        .description("닉네임"),
                    fieldWithPath("data.joinedDate").type(JsonFieldType.ARRAY)
                        .description("가입 일시")
                )
            ));
    }

    @DisplayName("회원 로그인 API")
    @Test
    void login() throws Exception {
        LoginMemberRequest request = LoginMemberRequest.builder()
            .email("temp@naver.com")
            .pwd("temp1234!")
            .build();

        mockMvc.perform(
                post(BASE_URL + "/login")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("login-member",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("pwd").type(JsonFieldType.STRING)
                        .description("비밀번호")
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
                    fieldWithPath("data.grantType").type(JsonFieldType.STRING)
                        .description("권한 유형"),
                    fieldWithPath("data.accessToken").type(JsonFieldType.STRING)
                        .description("접근 토큰"),
                    fieldWithPath("data.refreshToken").type(JsonFieldType.STRING)
                        .description("갱신 토큰")
                )
            ));
    }

    @DisplayName("이메일 인증 번호 요청 API")
    @Test
    void requestAuthenticationNumber() throws Exception {
        AuthenticationNumberRequest request = AuthenticationNumberRequest.builder()
            .email("temp@naver.com")
            .build();

        mockMvc.perform(
                post(BASE_URL + "/auth")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("request-authentication-number",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING)
                        .description("이메일")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.NULL)
                        .description("응답 데이터")
                )
            ));
    }

    @DisplayName("이메일 인증 번호 확인 API")
    @Test
    void checkAuthenticationNumber() throws Exception {
        CheckAuthenticationNumberRequest request = CheckAuthenticationNumberRequest.builder()
            .email("temp@naver.com")
            .authenticationNumber("1V4g6d16")
            .build();

        mockMvc.perform(
                post(BASE_URL + "/auth/check")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andDo(document("check-authentication-number",
                getDocumentRequest(),
                getDocumentResponse(),
                requestFields(
                    fieldWithPath("email").type(JsonFieldType.STRING)
                        .description("이메일"),
                    fieldWithPath("authenticationNumber").type(JsonFieldType.STRING)
                        .description("인증 번호")
                ),
                responseFields(
                    fieldWithPath("code").type(JsonFieldType.NUMBER)
                        .description("코드"),
                    fieldWithPath("status").type(JsonFieldType.STRING)
                        .description("상태"),
                    fieldWithPath("message").type(JsonFieldType.STRING)
                        .description("메시지"),
                    fieldWithPath("data").type(JsonFieldType.NULL)
                        .description("응답 데이터")
                )
            ));
    }
}
