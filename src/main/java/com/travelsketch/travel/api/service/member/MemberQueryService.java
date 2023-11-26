package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.domain.member.repository.MemberQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberQueryService {

    private final MemberQueryRepository memberQueryRepository;

    public MemberInfo searchMemberInfo(String email) {
        return null;
    }
}
