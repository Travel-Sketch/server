package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 회원 조회용 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    /**
     * 이메일로 회원 정보를 조회하여 반환한다.
     *
     * @param email 조회할 이메일
     * @return 조회된 회원 정보
     */
    public MemberInfo searchMemberInfo(String email) {
        Optional<MemberInfo> findMemberInfo = memberQueryRepository.findMemberInfoByEmail(email);
        if (findMemberInfo.isEmpty()) {
            throw new NoSuchElementException("일치하는 회원 정보가 존재하지 않습니다.");
        }
        return findMemberInfo.get();
    }
}
