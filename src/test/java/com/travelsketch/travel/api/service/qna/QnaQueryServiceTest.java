package com.travelsketch.travel.api.service.qna;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.qna.response.QnaResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.qna.Qna;
import com.travelsketch.travel.domain.qna.QnaType;
import com.travelsketch.travel.domain.qna.repository.QnaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class QnaQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private QnaQueryService qnaQueryService;

    @Autowired
    private QnaRepository qnaRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("검색 조건을 입력 받아 QnA를 조회할 수 있다.")
    @Test
    void searchQnas() {
        //given
        Member member = saveMember();
        Qna qna1 = saveQna(member, "카리나 최고인가요?", "1234", null);
        Qna qna2 = saveQna(member, "에스파 카리나 최고인가요?", "1234", "맞습니다");
        Qna qna3 = saveQna(member, "에스파 카리나", null, null);
        Qna qna4 = saveQna(member, "에스파 윈터입니다.", "1234", null);
        Qna qna5 = saveQna(member, "에스파 카리나 최고인가?", null, null);
        qna5.remove();

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        PageResponse<QnaResponse> response = qnaQueryService.searchQnas("카리나", pageRequest);

        //then
        assertThat(response.getContent()).hasSize(3)
            .extracting("title", "isLocked", "isAnswer")
            .containsExactlyInAnyOrder(
                tuple("카리나 최고인가요?", true, false),
                tuple("에스파 카리나 최고인가요?", true, true),
                tuple("에스파 카리나", false, false)
            );
    }

    private Member saveMember() {
        Member member = Member.builder()
            .email("karina@naver.com")
            .pwd(passwordEncoder.encode("karina1234!"))
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .role(Role.ADMIN)
            .build();
        return memberRepository.save(member);
    }

    private Qna saveQna(Member member, String title, String pwd, String answer) {
        Qna qna = Qna.builder()
            .member(member)
            .type(QnaType.ACCOUNT)
            .title(title)
            .content("QnA 내용입니다.")
            .pwd(pwd)
            .answer(answer)
            .build();
        return qnaRepository.save(qna);
    }
}