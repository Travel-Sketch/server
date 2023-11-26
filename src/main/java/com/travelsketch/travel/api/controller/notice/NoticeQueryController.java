package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
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

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notices")
public class NoticeQueryController {

    @GetMapping
    public ApiResponse<PageResponse<NoticeResponse>> searchNotices(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(required = false) String query
    ) {
        NoticeResponse response = NoticeResponse.builder()
            .noticeId(1L)
            .title("공지사항 제목입니다.")
            .createdDate(LocalDateTime.of(2023, 11, 26, 17, 11))
            .build();
        PageRequest pageRequest = PageRequest.of(0, 10);
        return ApiResponse.ok(new PageResponse<>(new PageImpl<>(List.of(response), pageRequest, 10)));
    }
}
