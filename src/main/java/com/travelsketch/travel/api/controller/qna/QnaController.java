package com.travelsketch.travel.api.controller.qna;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.qna.request.CreateAnswerRequest;
import com.travelsketch.travel.api.controller.qna.request.CreateQuestionRequest;
import com.travelsketch.travel.api.controller.qna.response.CreateAnswerResponse;
import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
import com.travelsketch.travel.api.controller.qna.response.RemoveQnaResponse;
import com.travelsketch.travel.api.service.qna.QnaService;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;
import static com.travelsketch.travel.api.controller.qna.QnaCustomValid.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/qna")
public class QnaController {

    private final QnaService qnaService;
    private final SecurityUtils securityUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateQuestionResponse> createQuestion(@Valid @RequestBody CreateQuestionRequest request) {
        validPwd(request.getPwd());

        String email = securityUtils.getCurrentEmail();

        CreateQuestionResponse response = qnaService.createQuestion(email, request.toDto());

        return created(response);
    }

    @PostMapping("/{qnaId}")
    public ApiResponse<CreateAnswerResponse> createAnswer(@PathVariable Long qnaId, @RequestBody CreateAnswerRequest request) {
        CreateAnswerResponse response = CreateAnswerResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("질문 제목입니다.")
            .answer("질문 답변입니다.")
            .modifiedDate(LocalDateTime.of(2023, 11, 27, 17, 32))
            .build();
        return ok(response);
    }

    @DeleteMapping("/{qnaId}")
    public ApiResponse<RemoveQnaResponse> removeQna(@PathVariable Long qnaId) {
        RemoveQnaResponse response = RemoveQnaResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("질문 제목입니다.")
            .removedDate(LocalDateTime.of(2023, 11, 27, 19, 0))
            .build();
        return ok(response);
    }
}
