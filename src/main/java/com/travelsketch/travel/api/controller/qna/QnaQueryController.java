package com.travelsketch.travel.api.controller.qna;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/qna")
public class QnaQueryController {

    @GetMapping
    public ApiResponse<PageResponse<QnaResponse>> searchQnas(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        QnaResponse response = QnaResponse.builder()
            .qnaId(1L)
            .type("계정")
            .title("공지사항 제목입니다.")
            .isLocked(true)
            .createdDate(LocalDateTime.of(2023, 11, 27, 19, 7))
            .build();
        PageRequest pageRequest = PageRequest.of(0, 10);

        return ok(new PageResponse<>(new PageImpl<>(List.of(response), pageRequest, 1)));
    }
}
