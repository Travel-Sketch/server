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

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CreateMemberResponse createMember(CreateMemberDto dto) {

        checkDuplicationForEmail(dto.email());
        checkDuplicationForNickname(dto.nickname());

        Member member = toEntity(dto);

        Member savedMember = memberRepository.save(member);

        return CreateMemberResponse.of(savedMember);
    }

    public boolean modifyPwd(String email, String currentPwd, String newPwd) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }
        Member member = findMember.get();

        boolean isMatched = passwordEncoder.matches(currentPwd, member.getPwd());
        if (!isMatched) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        String encodedPwd = passwordEncoder.encode(newPwd);

        Member modifiedMember = member.modifyPwd(encodedPwd);

        return true;
    }

    private void checkDuplicationForEmail(String email) {
        boolean isExistedEmail = memberQueryRepository.existEmail(email);
        if (isExistedEmail) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }
    }

    private void checkDuplicationForNickname(String nickname) {
        boolean isExistedNickname = memberQueryRepository.existNickname(nickname);
        if (isExistedNickname) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임 입니다.");
        }
    }

    private Member toEntity(CreateMemberDto dto) {
        String encodedPwd = passwordEncoder.encode(dto.pwd());

        return Member.builder()
            .email(dto.email())
            .pwd(encodedPwd)
            .name(dto.name())
            .birth(dto.birth())
            .gender(dto.gender())
            .nickname(dto.nickname())
            .role(Role.USER)
            .build();
    }
}
