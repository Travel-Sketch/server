package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.ControllerTestSupport;
import com.travelsketch.travel.api.controller.member.request.ModifyPwdRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/members";

    @DisplayName("비밀번호 변경시 현재 비밀번호는 필수값이다.")
    @Test
    void modifyPwdWithoutCurrentPwd() throws Exception {
        //given
        ModifyPwdRequest request = ModifyPwdRequest.builder()
            .currentPwd(" ")
            .newPwd("karina5678!")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/pwd")
                    .with(csrf())
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("현재 비밀번호는 필수입니다."));
    }

    @DisplayName("비밀번호 변경시 새로운 비밀번호는 최소 8자이다.")
    @Test
    void modifyPwdOutOfMinNewPwd() throws Exception {
        //given
        ModifyPwdRequest request = ModifyPwdRequest.builder()
            .currentPwd("karina1234!")
            .newPwd("karina!")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/pwd")
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

    @DisplayName("비밀번호 변경시 새로운 비밀번호는 최대 20자이다.")
    @Test
    void modifyPwdOutOfMaxNewPwd() throws Exception {
        //given
        ModifyPwdRequest request = ModifyPwdRequest.builder()
            .currentPwd("karina1234!")
            .newPwd("asepakarina1234567890")
            .build();

        //when //then
        mockMvc.perform(
                patch(BASE_URL + "/pwd")
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
}