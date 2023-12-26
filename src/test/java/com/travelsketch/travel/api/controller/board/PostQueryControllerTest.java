package com.travelsketch.travel.api.controller.board;

import com.travelsketch.travel.ControllerTestSupport;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PostQueryControllerTest extends ControllerTestSupport {

    private static final String BASE_URL = "/api/v1/posts";

    @DisplayName("페이지 번호가 유효해야 한다.")
    @Test
    void searchPosts() throws Exception {
        mockMvc.perform(
                get(BASE_URL)
                    .with(csrf())
                    .queryParam("page", "0")
            )
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("페이지 번호가 유효하지 않습니다."));
    }


}