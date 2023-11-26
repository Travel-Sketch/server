package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import com.travelsketch.travel.domain.notice.repository.NoticeQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    public PageResponse<NoticeResponse> searchByCond(String query, Pageable pageable) {
        List<NoticeResponse> responses = noticeQueryRepository.findByCond(query, pageable);

        int count = noticeQueryRepository.findCountByCond(query);

        PageImpl<NoticeResponse> content = new PageImpl<>(responses, pageable, count);

        return new PageResponse<>(content);
    }

    public NoticeDetailResponse searchById(Long noticeId) {
        return null;
    }
}
