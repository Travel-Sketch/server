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

import static com.travelsketch.travel.api.ApiResponse.ok;

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
        if (isNegativeOrZero(page)) {
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<QnaResponse> response = qnaQueryService.searchQnas(query, pageRequest);

        return ok(response);
    }

    @GetMapping("/{qnaId}")
    public ApiResponse<QnaDetailResponse> searchQna(@PathVariable Long qnaId) {

        QnaDetailResponse response = qnaQueryService.searchQna(qnaId, "1234");

        return ok(response);
    }

    private boolean isNegativeOrZero(int number) {
        return number <= 0;
    }
}
