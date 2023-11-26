package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.Notice;
import com.travelsketch.travel.domain.notice.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class NoticeServiceTest extends IntegrationTestSupport {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일, 제목, 내용을 입력 받아 공지사항을 등록할 수 있다.")
    @Test
    void createNotice() {
        //given
        Member member = savedMember();

        //when
        CreateNoticeResponse response = noticeService.createNotice("karina@naver.com", "공지사항 제목입니다.", "공지사항 내용입니다.");

        //then
        Optional<Notice> findNotice = noticeRepository.findById(response.getNoticeId());
        assertThat(findNotice).isPresent();
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