package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
import com.travelsketch.travel.api.controller.notice.response.ModifyNoticeResponse;
import com.travelsketch.travel.api.controller.notice.response.RemoveNoticeResponse;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.Notice;
import com.travelsketch.travel.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public CreateNoticeResponse createNotice(String email, String title, String content) {
        Member member = getMember(email);

        Notice notice = toEntity(title, content, member);

        Notice savedNotice = noticeRepository.save(notice);

        return CreateNoticeResponse.of(savedNotice);
    }

    public ModifyNoticeResponse modifyNotice(String email, Long noticeId, String title, String content) {
        Notice notice = getNotice(noticeId);

        Member member = getMember(email);

        Notice modifiedNotice = notice.modify(title, content, member);

        return ModifyNoticeResponse.of(modifiedNotice);
    }

    public RemoveNoticeResponse removeNotice(String email, Long noticeId) {
        Notice notice = getNotice(noticeId);

        Member member = getMember(email);

        notice.remove(member);

        return RemoveNoticeResponse.of(notice);
    }

    private Notice toEntity(String title, String content, Member member) {
        return Notice.builder()
            .title(title)
            .content(content)
            .member(member)
            .build();
    }

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }

    private Notice getNotice(Long noticeId) {
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        if (findNotice.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 공지사항입니다.");
        }
        return findNotice.get();
    }
}
