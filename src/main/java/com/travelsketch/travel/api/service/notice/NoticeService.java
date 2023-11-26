package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
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
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }
        Member member = findMember.get();

        Notice notice = Notice.builder()
            .title(title)
            .content(content)
            .member(member)
            .build();
        Notice savedNotice = noticeRepository.save(notice);

        return CreateNoticeResponse.of(savedNotice);
    }
}
