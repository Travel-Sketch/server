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

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;
import static com.travelsketch.travel.api.controller.qna.QnaCustomValid.validPwd;

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
    public ApiResponse<CreateAnswerResponse> createAnswer(@PathVariable Long qnaId, @Valid @RequestBody CreateAnswerRequest request) {
        String email = securityUtils.getCurrentEmail();

        CreateAnswerResponse response = qnaService.createAnswer(email, qnaId, request.getAnswer());

        return ok(response);
    }

    @DeleteMapping("/{qnaId}")
    public ApiResponse<RemoveQnaResponse> removeQna(@PathVariable Long qnaId) {
        String email = securityUtils.getCurrentEmail();

        RemoveQnaResponse response = qnaService.removeQna(email, qnaId);

        return ok(response);
    }
}
