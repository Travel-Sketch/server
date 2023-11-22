package com.travelsketch.travel.docs.member;

import com.travelsketch.travel.api.controller.member.AccountController;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
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
}
