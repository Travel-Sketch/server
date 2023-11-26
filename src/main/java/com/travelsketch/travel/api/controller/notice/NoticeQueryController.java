package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import com.travelsketch.travel.api.service.notice.NoticeQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notices")
public class NoticeQueryController {

    private final NoticeQueryService noticeQueryService;

    @GetMapping
    public ApiResponse<PageResponse<NoticeResponse>> searchNotices(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        if (page <= 0) {
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<NoticeResponse> response = noticeQueryService.searchByCond(query, pageRequest);

        return ok(response);
    }

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDetailResponse> searchNotice(@PathVariable Long noticeId) {

        NoticeDetailResponse response = noticeQueryService.searchById(noticeId);

        return ok(response);

    }
}
