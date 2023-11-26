package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.ControllerTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NoticeQueryControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/notices";

    @DisplayName("페이지는 반드시 1이상이다.")
    @Test
    void searchNotices() throws Exception {
        //given //when //then
        mockMvc.perform(
                get(BASE_URL)
                    .with(csrf())
                    .queryParam("page", "0")
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("페이지는 1이상입니다."));
    }
}