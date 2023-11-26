package com.travelsketch.travel.domain.notice.repository;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.Notice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class NoticeQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private NoticeQueryRepository noticeQueryRepository;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("검색 조건에 맞는 공지사항을 조회한다.")
    @Test
    void findByCond() {
        //given
        Member member = saveMember();
        Notice notice1 = saveNotice(member, "카리나 최고입니다.");
        Notice notice2 = saveNotice(member, "에스파 카리나 최고입니다.");
        Notice notice3 = saveNotice(member, "에스파 카리나");
        Notice notice4 = saveNotice(member, "에스파 윈터도 최고입니다.");
        Notice notice5 = saveNotice(member, "에스파 카리나 1위");
        notice5.remove();

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        List<NoticeResponse> responses = noticeQueryRepository.findByCond("카리나", pageRequest);

        //then
        assertThat(responses).hasSize(3)
            .extracting("noticeId", "title")
            .containsExactlyInAnyOrder(
                tuple(notice1.getId(), "카리나 최고입니다."),
                tuple(notice2.getId(), "에스파 카리나 최고입니다."),
                tuple(notice3.getId(), "에스파 카리나")
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

    private Notice saveNotice(Member member, String title) {
        Notice notice = Notice.builder()
            .title(title)
            .content("공지사항 내용입니다.")
            .member(member)
            .build();
        return noticeRepository.save(notice);
    }
}