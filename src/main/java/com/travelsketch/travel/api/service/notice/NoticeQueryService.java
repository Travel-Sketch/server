package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import com.travelsketch.travel.domain.notice.repository.NoticeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    public PageResponse<NoticeResponse> searchByCond(String query, Pageable pageable) {
        return null;
    }
}
