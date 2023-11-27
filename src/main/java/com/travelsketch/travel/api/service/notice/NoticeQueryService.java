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
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 공지사항 조회용 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class NoticeQueryService {

    private final NoticeQueryRepository noticeQueryRepository;

    /**
     * 검색 조건으로 공지사항 목록을 조회하여 반환한다.
     *
     * @param query    검색 조건
     * @param pageable 페이징 정보
     * @return 조회된 공지사항 목록 정보
     */
    public PageResponse<NoticeResponse> searchByCond(String query, Pageable pageable) {
        List<NoticeResponse> responses = noticeQueryRepository.findByCond(query, pageable);

        int count = noticeQueryRepository.findCountByCond(query);

        PageImpl<NoticeResponse> content = new PageImpl<>(responses, pageable, count);

        return new PageResponse<>(content);
    }

    /**
     * 아이디로 공지사항을 조회하여 반환한다.
     *
     * @param noticeId 조회할 아이디
     * @return 조회된 공지사항 상세 정보
     * @throws NoSuchElementException   아이디가 일치하는 공지사항이 존재하지 않을 경우 발생
     * @throws IllegalArgumentException 조회된 공지사항이 삭제된 경우 발생
     */
    public NoticeDetailResponse searchById(Long noticeId) {
        Optional<NoticeDetailResponse> findResponse = noticeQueryRepository.findById(noticeId);
        if (findResponse.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 공지사항입니다.");
        }
        NoticeDetailResponse response = findResponse.get();

        if (response.getIsDeleted()) {
            throw new IllegalArgumentException("삭제된 공지사항입니다.");
        }

        return response;
    }
}
