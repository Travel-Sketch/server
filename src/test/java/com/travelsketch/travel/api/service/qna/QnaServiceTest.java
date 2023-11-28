package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.qna.response.CreateQuestionResponse;
import com.travelsketch.travel.api.service.qna.dto.CreateQuestionDto;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.qna.Qna;
import com.travelsketch.travel.domain.qna.QnaType;
import com.travelsketch.travel.domain.qna.repository.QnaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class QnaServiceTest extends IntegrationTestSupport {

    @Autowired
    private QnaService qnaService;

    @Autowired
    private QnaRepository qnaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일, 질문 유형, 제목, 내용, 비밀번호를 입력 받아 질문을 등록할 수 있다.")
    @Test
    void createQuestion() {
        //given
        Member member = savedMember();
        CreateQuestionDto dto = CreateQuestionDto.builder()
            .type(QnaType.ACCOUNT)
            .title("QnA 질문입니다.")
            .content("QnA 내용입니다.")
            .pwd("1234")
            .build();

        //when
        CreateQuestionResponse response = qnaService.createQuestion("karina@naver.com", dto);

        //then
        Optional<Qna> findQna = qnaRepository.findById(response.getQnaId());
        assertThat(findQna).isPresent();
    }

    private Member savedMember() {
        Member member = Member.builder()
            .email("karina@naver.com")
            .pwd(passwordEncoder.encode("karina1234!"))
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .role(Role.USER)
            .build();
        return memberRepository.save(member);
    }
}