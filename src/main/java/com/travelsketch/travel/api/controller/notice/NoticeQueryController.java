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

/**
 * 공지사항 조회용 API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notices")
public class NoticeQueryController {

    private final NoticeQueryService noticeQueryService;

    /**
     * 조건에 따른 공지사항 목록 조회 API
     *
     * @param page  조회할 페이지 번호
     * @param query 조회할 검색 쿼리
     * @return 조회된 공지사항 목록
     */
    @GetMapping
    public ApiResponse<PageResponse<NoticeResponse>> searchNotices(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        if (isNegativeOrZero(page)) {
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<NoticeResponse> response = noticeQueryService.searchByCond(query, pageRequest);

        return ok(response);
    }

    /**
     * 공지사항 상세 조회 API
     *
     * @param noticeId 조회할 공지사항 아이디
     * @return 조회된 공지사항 상세 내역
     */
    @GetMapping("/{noticeId}")
    public ApiResponse<NoticeDetailResponse> searchNotice(@PathVariable Long noticeId) {

        NoticeDetailResponse response = noticeQueryService.searchById(noticeId);

        return ok(response);

    }

    /**
     * 입력 받은 숫자가 양수인 경우 true를 반환한다.
     *
     * @param number 검증 숫자
     * @return 양수인 경우 true 반환
     */
    private boolean isNegativeOrZero(int number) {
        return number <= 0;
    }
}
