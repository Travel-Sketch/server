package com.travelsketch.travel.api.controller.notice;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.notice.request.CreateNoticeRequest;
import com.travelsketch.travel.api.controller.notice.request.ModifyNoticeRequest;
import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
import com.travelsketch.travel.api.controller.notice.response.ModifyNoticeResponse;
import com.travelsketch.travel.api.controller.notice.response.RemoveNoticeResponse;
import com.travelsketch.travel.api.service.notice.NoticeService;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;

/**
 * 공지사항 API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;
    private final SecurityUtils securityUtils;

    /**
     * 공지사항 등록 API
     *
     * @param request 등록할 공지사항 정보
     * @return 저장된 공지사항 정보
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateNoticeResponse> createNotice(@Valid @RequestBody CreateNoticeRequest request) {
        String email = securityUtils.getCurrentEmail();

        CreateNoticeResponse response = noticeService.createNotice(email, request.getTitle(), request.getContent());

        return created(response);
    }

    /**
     * 공지사항 수정 API
     *
     * @param noticeId 수정할 공지사항 아이디
     * @param request  수정할 공지사항 정보
     * @return 수정된 공지사항 정보
     */
    @PatchMapping("/{noticeId}")
    public ApiResponse<ModifyNoticeResponse> modifyNotice(@PathVariable Long noticeId, @Valid @RequestBody ModifyNoticeRequest request) {
        String email = securityUtils.getCurrentEmail();

        ModifyNoticeResponse response = noticeService.modifyNotice(email, noticeId, request.getTitle(), request.getContent());

        return ok(response);
    }

    /**
     * 공자사항 삭제 API
     *
     * @param noticeId 삭제할 공지사항 아이디
     * @return 삭제된 공지사항 정보
     */
    @DeleteMapping("/{noticeId}")
    public ApiResponse<RemoveNoticeResponse> removeNotice(@PathVariable Long noticeId) {
        String email = securityUtils.getCurrentEmail();

        RemoveNoticeResponse response = noticeService.removeNotice(email, noticeId);

        return ok(response);
    }
}
