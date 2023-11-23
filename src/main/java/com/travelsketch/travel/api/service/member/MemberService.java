package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.service.member.dto.CreateMemberDto;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberQueryRepository;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateMemberResponse createMember(CreateMemberDto dto) {
        boolean isExistedEmail = memberQueryRepository.existEmail(dto.email());
        if (isExistedEmail) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        boolean isExistedNickname = memberQueryRepository.existNickname(dto.nickname());
        if (isExistedNickname) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임 입니다.");
        }

        String encodedPwd = passwordEncoder.encode(dto.pwd());

        Member member = Member.builder()
            .email(dto.email())
            .pwd(encodedPwd)
            .name(dto.name())
            .birth(dto.birth())
            .gender(dto.gender())
            .nickname(dto.nickname())
            .role(Role.USER)
            .build();

        Member savedMember = memberRepository.save(member);

        return CreateMemberResponse.of(savedMember);
    }
}
