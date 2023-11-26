package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeDetailResponse;
import com.travelsketch.travel.api.controller.notice.response.NoticeResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.Notice;
import com.travelsketch.travel.domain.notice.repository.NoticeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

class NoticeQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private NoticeQueryService noticeQueryService;

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("검색 쿼리, 페이징 정보를 입력 받아 공지사항 목록 조회를 할 수 있다.")
    @Test
    void searchByCond() {
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
        PageResponse<NoticeResponse> response = noticeQueryService.searchByCond("카리나", pageRequest);

        //then
        assertThat(response.getContent()).hasSize(3)
            .extracting("noticeId", "title")
            .containsExactlyInAnyOrder(
                tuple(notice1.getId(), "카리나 최고입니다."),
                tuple(notice2.getId(), "에스파 카리나 최고입니다."),
                tuple(notice3.getId(), "에스파 카리나")
            );
    }

    @DisplayName("입력 받은 아이디와 일치하는 공지사항이 존재하지 않으면 예외가 발생한다,")
    @Test
    void searchByIdNotExist() {
        //given


        //when //then
        assertThatThrownBy(() -> noticeQueryService.searchById(1L))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 공지사항입니다.");
    }

    @DisplayName("입력 받은 아이디로 조회된 공지사항이 삭제된 내역이라면 예외가 발생한다.")
    @Test
    void searchByIdIsDeleted() {
        //given
        Member member = saveMember();
        Notice notice = saveNotice(member, "공지사항 제목입니다.");
        notice.remove();

        //when //then
        assertThatThrownBy(() -> noticeQueryService.searchById(notice.getId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("삭제된 공지사항입니다.");
    }

    @DisplayName("아이디를 입력 받아 공지사항을 조회할 수 있다.")
    @Test
    void searchById() {
        //given
        Member member = saveMember();
        Notice notice = saveNotice(member, "공지사항 제목입니다.");

        //when
        NoticeDetailResponse response = noticeQueryService.searchById(notice.getId());

        //then
        assertThat(response.getTitle()).isEqualTo("공지사항 제목입니다.");
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