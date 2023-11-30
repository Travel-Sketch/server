package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.domain.qna.repository.QnaQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QnaQueryService {

    private final QnaQueryRepository qnaQueryRepository;

    public PageResponse<QnaResponse> searchQnas(String query, Pageable pageable) {
        return null;
    }
}
