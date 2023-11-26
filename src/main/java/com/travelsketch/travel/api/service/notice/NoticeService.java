package com.travelsketch.travel.api.service.notice;

import com.travelsketch.travel.api.controller.notice.response.CreateNoticeResponse;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    public CreateNoticeResponse createNotice(String email, String title, String content) {
        return null;
    }
}
