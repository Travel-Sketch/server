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

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;
    private final SecurityUtils securityUtils;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateNoticeResponse> createNotice(@Valid @RequestBody CreateNoticeRequest request) {
        String email = securityUtils.getCurrentEmail();

        CreateNoticeResponse response = noticeService.createNotice(email, request.getTitle(), request.getContent());

        return created(response);
    }

    @PatchMapping("/{noticeId}")
    public ApiResponse<ModifyNoticeResponse> modifyNotice(@PathVariable Long noticeId, @RequestBody ModifyNoticeRequest request) {
        ModifyNoticeResponse response = ModifyNoticeResponse.builder()
            .noticeId(1L)
            .title("수정된 공지사항 제목입니다.")
            .modifiedDate(LocalDateTime.of(2023, 11, 26, 16, 56))
            .build();

        return ok(response);
    }

    @DeleteMapping("/{noticeId}")
    public ApiResponse<RemoveNoticeResponse> removeNotice(@PathVariable Long noticeId) {
        RemoveNoticeResponse response = RemoveNoticeResponse.builder()
            .noticeId(1L)
            .title("삭제된 공지사항 제목입니다.")
            .removedDate(LocalDateTime.of(2023, 11, 26, 17, 2))
            .build();
        return ok(response);
    }
}
