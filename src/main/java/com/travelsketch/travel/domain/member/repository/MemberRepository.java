package com.travelsketch.travel.domain.member.repository;

import com.travelsketch.travel.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 저장소
 *
 * @author 임우택
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 회원 이메일로 회원 엔티티를 조회하여 반환한다.
     *
     * @param email 조회할 이메일
     * @return 조회된 회원 엔티티
     */
    Optional<Member> findByEmail(String email);
}
