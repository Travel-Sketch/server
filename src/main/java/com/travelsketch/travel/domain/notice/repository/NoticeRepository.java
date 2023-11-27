package com.travelsketch.travel.domain.notice.repository;

import com.travelsketch.travel.domain.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 공지사항 저장소
 *
 * @author 임우택
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
