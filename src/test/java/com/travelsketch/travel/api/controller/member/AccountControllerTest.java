package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AccountControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1";

    @DisplayName("회원 가입시 이메일은 필수값이다.")
    @Test
    void joinWithoutEmail() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("이메일은 필수입니다."));
    }

    @DisplayName("회원 가입시 이메일의 길이는 최대 100자이다.")
    @Test
    void joinOutOfEmailSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email(getText(91) + "@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("이메일의 길이는 최대 100자 입니다."));
    }

    @DisplayName("회원 가입시 비밀번호는 최소 8자이다.")
    @Test
    void joinOutOfPwdMinSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("qwer12!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("비밀번호는 최소 8자, 최대 20자 입니다."));
    }

    @DisplayName("회원 가입시 비밀번호는 최대 20자이다.")
    @Test
    void joinOutOfPwdMaxSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("qwertyuiop0123456789!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("비밀번호는 최소 8자, 최대 20자 입니다."));
    }

    @DisplayName("회원 가입시 이름은 필수값이다.")
    @Test
    void joinWithoutName() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name(" ")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("이름은 필수입니다."));
    }

    @DisplayName("회원 가입시 이름은 최대 10자이다.")
    @Test
    void joinOutOfNameSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name(getText(11))
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("이름의 길이는 최대 10자 입니다."));
    }

    @DisplayName("회원 가입시 생년월일은 필수값이다.")
    @Test
    void joinWithoutBirth() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("생년월일은 필수입니다."));
    }

    @DisplayName("회원 가입시 생년월일은 현재 날짜 기준으로 과거만 가능하다.")
    @Test
    void joinPastBirth() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.now().plusDays(1))
            .gender("F")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("생년월일을 확인해주세요."));
    }

    @DisplayName("회원 가입시 성별은 최대 1자이다.")
    @Test
    void joinOutOfGenderSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("MA")
            .nickname("카리나")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("성별은 반드시 1자입니다."));
    }

    @DisplayName("회원 가입시 닉네임은 필수값이다.")
    @Test
    void joinWithoutNickname() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname(" ")
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("닉네임은 필수입니다."));
    }

    @DisplayName("회원 가입시 닉네임은 최대 10자이다.")
    @Test
    void joinOutOfNicknameSize() throws Exception {
        //given
        CreateMemberRequest request = CreateMemberRequest.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth(LocalDate.of(2000, 4, 11))
            .gender("F")
            .nickname(getText(11))
            .build();

        //when //then
        mockMvc.perform(
                post(BASE_URL + "/join")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("닉네임은 최대 10자 입니다."));
    }

    private String getText(int size) {
        return "a".repeat(Math.max(0, size));
    }
}