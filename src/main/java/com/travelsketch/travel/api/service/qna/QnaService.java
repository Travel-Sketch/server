package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
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

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }
}
