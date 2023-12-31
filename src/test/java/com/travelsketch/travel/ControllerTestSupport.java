package com.travelsketch.travel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelsketch.travel.api.controller.board.CommentController;
import com.travelsketch.travel.api.controller.board.PostController;
import com.travelsketch.travel.api.controller.board.PostQueryController;
import com.travelsketch.travel.api.controller.member.AccountController;
import com.travelsketch.travel.api.controller.member.MemberController;
import com.travelsketch.travel.api.controller.member.MemberQueryController;
import com.travelsketch.travel.api.controller.notice.NoticeController;
import com.travelsketch.travel.api.controller.notice.NoticeQueryController;
import com.travelsketch.travel.api.controller.plan.PlanController;
import com.travelsketch.travel.api.controller.qna.QnaController;
import com.travelsketch.travel.api.controller.qna.QnaQueryController;
import com.travelsketch.travel.api.service.board.CommentService;
import com.travelsketch.travel.api.service.board.FileStore;
import com.travelsketch.travel.api.service.board.PostQueryService;
import com.travelsketch.travel.api.service.board.PostService;
import com.travelsketch.travel.api.service.member.AccountService;
import com.travelsketch.travel.api.service.member.MemberQueryService;
import com.travelsketch.travel.api.service.member.MemberService;
import com.travelsketch.travel.api.service.notice.NoticeQueryService;
import com.travelsketch.travel.api.service.notice.NoticeService;
import com.travelsketch.travel.api.service.plan.PlanService;
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
    QnaController.class, QnaQueryController.class,
    PlanController.class, PostController.class, PostQueryController.class, CommentController.class

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

    @MockBean
    protected PostService postService;

    @MockBean
    protected FileStore fileStore;

    @MockBean
    protected PlanService planService;

    @MockBean
    protected PostQueryService postQueryService;

    @MockBean
    protected CommentService commentService;

}
