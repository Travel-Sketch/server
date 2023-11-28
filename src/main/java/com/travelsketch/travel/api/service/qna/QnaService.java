package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
import com.travelsketch.travel.api.service.qna.dto.CreateQuestionDto;
import com.travelsketch.travel.domain.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;

    public CreateQuestionResponse createQuestion(String email, CreateQuestionDto dto) {
        return null;
    }
}
