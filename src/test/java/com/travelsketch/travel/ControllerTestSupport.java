package com.travelsketch.travel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelsketch.travel.api.controller.member.AccountController;
import com.travelsketch.travel.api.service.member.AccountService;
import com.travelsketch.travel.api.service.member.MemberService;
import com.travelsketch.travel.interceptor.query.ApiQueryCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(controllers = {AccountController.class})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ApiQueryCounter apiQueryCounter;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected AccountService accountService;
}
