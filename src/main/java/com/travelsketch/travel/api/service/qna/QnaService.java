package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.controller.qna.response.CreateAnswerResponse;
import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
import com.travelsketch.travel.api.controller.qna.response.RemoveQnaResponse;
import com.travelsketch.travel.api.service.qna.dto.CreateQuestionDto;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.qna.Qna;
import com.travelsketch.travel.domain.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Service
@Transactional
public class QnaService {

    private final QnaRepository qnaRepository;
    private final MemberRepository memberRepository;

    public CreateQuestionResponse createQuestion(String email, CreateQuestionDto dto) {
        Member member = getMember(email);

        Qna qna = Qna.builder()
            .member(member)
            .type(dto.type())
            .title(dto.title())
            .content(dto.content())
            .pwd(dto.pwd())
            .answer(null)
            .build();

        Qna savedQna = qnaRepository.save(qna);

        return CreateQuestionResponse.of(savedQna);
    }

    public CreateAnswerResponse createAnswer(String email, Long qnaId, String answer) {
        Optional<Qna> findQna = qnaRepository.findById(qnaId);
        if (findQna.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 QnA입니다.");
        }
        Qna qna = findQna.get();

        if (hasText(qna.getAnswer())) {
            throw new IllegalArgumentException("이미 답변이 등록된 QnA입니다.");
        }

        Member member = getMember(email);

        Qna modifiedQna = qna.createAnswer(member, answer);

        return CreateAnswerResponse.of(modifiedQna);
    }

    public RemoveQnaResponse removeQna(Long qnaId) {
        return null;
    }

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }
}
