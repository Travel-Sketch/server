package com.travelsketch.travel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelsketch.travel.api.controller.member.AccountController;
import com.travelsketch.travel.api.controller.member.MemberController;
import com.travelsketch.travel.api.controller.member.MemberQueryController;
import com.travelsketch.travel.api.controller.notice.NoticeController;
import com.travelsketch.travel.api.controller.notice.NoticeQueryController;
import com.travelsketch.travel.api.controller.qna.QnaController;
import com.travelsketch.travel.api.controller.qna.QnaQueryController;
import com.travelsketch.travel.api.service.member.AccountService;
import com.travelsketch.travel.api.service.member.MemberQueryService;
import com.travelsketch.travel.api.service.member.MemberService;
import com.travelsketch.travel.api.service.notice.NoticeQueryService;
import com.travelsketch.travel.api.service.notice.NoticeService;
import com.travelsketch.travel.api.service.qna.QnaQueryService;
import com.travelsketch.travel.api.service.qna.QnaService;
import com.travelsketch.travel.interceptor.query.ApiQueryCounter;
import com.travelsketch.travel.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WithMockUser
@WebMvcTest(controllers = {AccountController.class, MemberController.class, MemberQueryController.class,
    NoticeController.class, NoticeQueryController.class,
    QnaController.class, QnaQueryController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ApiQueryCounter apiQueryCounter;

    @MockBean
    protected SecurityUtils securityUtils;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected MemberQueryService memberQueryService;

    @MockBean
    protected AccountService accountService;

    @MockBean
    protected NoticeService noticeService;

    @MockBean
    protected NoticeQueryService noticeQueryService;

    @MockBean
    protected QnaService qnaService;

    @MockBean
    protected QnaQueryService qnaQueryService;
}
