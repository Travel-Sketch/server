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

/**
 * 공지사항 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    /**
     * 새로운 공지사항을 저장하고 저장 결과를 반환한다.
     *
     * @param email   작성자 이메일
     * @param title   등록할 공지사항 제목
     * @param content 등록할 공지사항 내용
     * @return 저장된 공지사항 내용
     */
    public CreateNoticeResponse createNotice(String email, String title, String content) {
        Member member = getMember(email);

        Notice notice = toEntity(title, content, member);

        Notice savedNotice = noticeRepository.save(notice);

        return CreateNoticeResponse.of(savedNotice);
    }

    /**
     * 공지사항을 수정하고 수정 결과를 반환한다.
     *
     * @param email    수정자 이메일
     * @param noticeId 수정할 공지사항 아이디
     * @param title    수정할 공지사항 제목
     * @param content  수정할 공지사항 내용
     * @return 수정된 공지사항 내용
     */
    public ModifyNoticeResponse modifyNotice(String email, Long noticeId, String title, String content) {
        Notice notice = getNotice(noticeId);

        Member member = getMember(email);

        Notice modifiedNotice = notice.modify(title, content, member);

        return ModifyNoticeResponse.of(modifiedNotice);
    }

    /**
     * 공지사항을 삭제하고 삭제 결과를 반환한다.
     *
     * @param email    삭제한 관리자 이메일
     * @param noticeId 삭제할 공지사항 아이디
     * @return 삭제된 공지사항 내용
     */
    public RemoveNoticeResponse removeNotice(String email, Long noticeId) {
        Notice notice = getNotice(noticeId);

        Member member = getMember(email);

        notice.remove(member);

        return RemoveNoticeResponse.of(notice);
    }

    /**
     * 공지사항 엔티티를 생성하여 반환한다.
     *
     * @param title   공지사항 제목
     * @param content 공지사항 내용
     * @param member  공지사항 작성자
     * @return 생성된 공지사항 엔티티
     */
    private Notice toEntity(String title, String content, Member member) {
        return Notice.builder()
            .title(title)
            .content(content)
            .member(member)
            .build();
    }

    /**
     * 이메일로 회원 엔티티를 조회하여 반환한다.
     *
     * @param email 조회할 이메일
     * @return 조회된 회원 엔티티
     * @throws NoSuchElementException 이메일이 일치하는 회원이 존재하지 않을 경우 발생
     */
    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }

    /**
     * 아이디로 공지사항 엔티티를 조회하여 반환한다.
     *
     * @param noticeId 조회할 아이디
     * @return 조회된 공지사항 엔티티
     * @throws NoSuchElementException 아이디가 일치하는 공지사항이 존재하지 않을 경우 발생
     */
    private Notice getNotice(Long noticeId) {
        Optional<Notice> findNotice = noticeRepository.findById(noticeId);
        if (findNotice.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 공지사항입니다.");
        }
        return findNotice.get();
    }
}
