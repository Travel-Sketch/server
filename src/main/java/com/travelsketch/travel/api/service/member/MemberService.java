package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.service.member.dto.CreateMemberDto;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public CreateMemberResponse createMember(CreateMemberDto dto) {
        return null;
    }
}
