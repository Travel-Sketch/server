package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberInfo searchMemberInfo(String email) {
        Optional<MemberInfo> findMemberInfo = memberQueryRepository.findMemberInfoByEmail(email);
        if (findMemberInfo.isEmpty()) {
            throw new NoSuchElementException("일치하는 회원 정보가 존재하지 않습니다.");
        }
        return findMemberInfo.get();
    }
}
