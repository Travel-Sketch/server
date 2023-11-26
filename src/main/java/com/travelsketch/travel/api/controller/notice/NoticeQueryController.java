package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.*;

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
        return ok(new PageResponse<>(new PageImpl<>(List.of(response), pageRequest, 10)));
    }

    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDetailResponse> searchNotice(@PathVariable Long noticeId) {

        NoticeDetailResponse response = NoticeDetailResponse.builder()
            .noticeId(1L)
            .title("공지사항 제목입니다.")
            .content("공지사항 내용입니다.")
            .createdDate(LocalDateTime.of(2023, 11, 26, 17, 30))
            .build();

        return ok(response);

    }
}
