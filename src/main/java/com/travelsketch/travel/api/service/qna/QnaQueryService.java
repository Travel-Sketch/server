package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaDetailResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.domain.qna.Qna;
import com.travelsketch.travel.domain.qna.repository.QnaQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

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

    public QnaDetailResponse searchQna(Long qnaId, String pwd) {
        Qna qna = getQnaEntity(qnaId);

        checkDeleted(qna);

        if (hasText(qna.getPwd()) && !qna.getPwd().equals(pwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return QnaDetailResponse.of(qna);
    }

    private void checkDeleted(Qna qna) {
        if (qna.getIsDeleted()) {
            throw new IllegalArgumentException("삭제된 QnA입니다.");
        }
    }

    private Qna getQnaEntity(Long qnaId) {
        Optional<Qna> findQna = qnaQueryRepository.findById(qnaId);
        if (findQna.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 QnA입니다.");
        }
        return findQna.get();
    }
}
