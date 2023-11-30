package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaDetailResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.domain.qna.repository.QnaQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QnaQueryService {

    private final QnaQueryRepository qnaQueryRepository;

    public PageResponse<QnaResponse> searchQnas(String query, Pageable pageable) {
        List<QnaResponse> responses = qnaQueryRepository.findByCond(query, pageable);

        int count = qnaQueryRepository.findCountByCond(query);

        PageImpl<QnaResponse> content = new PageImpl<>(responses, pageable, count);

        return new PageResponse<>(content);
    }

    public QnaDetailResponse searchQna(Long qnaId) {
        return null;
    }
}
