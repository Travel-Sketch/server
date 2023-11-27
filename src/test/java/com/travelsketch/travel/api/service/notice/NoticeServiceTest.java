package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
import com.travelsketch.travel.api.controller.notice.response.ModifyNoticeResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.Notice;
import com.travelsketch.travel.domain.notice.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("입력 받은 아이디와 일치하는 공지사항이 존재하지 않으면 예외가 발생한다.")
    @Test
    void modifyNoticeNoSuchNotice() {
        // given
        Member member = savedMember();

        // when //then
        assertThatThrownBy(() -> noticeService.modifyNotice("karina@naver.com", 1L, "수정된 공지사항 제목입니다.", "수정된 공지사항 내용입니다."))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 공지사항입니다.");
    }

    @DisplayName("이메일, 아이디, 제목, 내용을 입력 받아 공지사항을 수정할 수 있다.")
    @Test
    void modifyNotice() {
        //given
        Member member = savedMember();
        Notice notice = saveNotice(member);

        //when
        ModifyNoticeResponse response = noticeService.modifyNotice("karina@naver.com", notice.getId(), "수정된 공지사항 제목입니다.", "수정된 공지사항 내용입니다.");

        //then
        Optional<Notice> findNotice = noticeRepository.findById(notice.getId());
        assertThat(findNotice).isPresent();
        assertThat(findNotice.get().getTitle()).isEqualTo("수정된 공지사항 제목입니다.");
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

    private Notice saveNotice(Member member) {
        Notice notice = Notice.builder()
            .title("공지사항 제목입니다.")
            .content("공지사항 내용입니다.")
            .member(member)
            .build();
        return noticeRepository.save(notice);
    }
}