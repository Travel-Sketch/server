package com.travelsketch.travel.api.controller.qna;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaDetailResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.api.service.qna.QnaQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.ok;
import static com.travelsketch.travel.domain.qna.QnaType.ACCOUNT;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/qna")
public class QnaQueryController {

    private final QnaQueryService qnaQueryService;

    @GetMapping
    public ApiResponse<PageResponse<QnaResponse>> searchQnas(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<QnaResponse> response = qnaQueryService.searchQnas(query, pageRequest);

        return ok(response);
    }

    @GetMapping("/{qnaId}")
    public ApiResponse<QnaDetailResponse> searchQna(@PathVariable Long qnaId) {
        QnaDetailResponse response  = QnaDetailResponse.builder()
            .qnaId(1L)
            .type(ACCOUNT)
            .title("QnA 제목입니다.")
            .content("QnA 내용입니다.")
            .answer("QnA 답변입니다.")
            .createdDate(LocalDateTime.of(2023, 11, 27, 9, 27))
            .build();
        return ok(response);
    }
}
