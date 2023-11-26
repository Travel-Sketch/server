package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.controller.member.response.ModifyNicknameResponse;
import com.travelsketch.travel.api.controller.member.response.WithdrawalMemberResponse;
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

        checkCurrentPwd(currentPwd, member.getPwd());

        String encodedPwd = passwordEncoder.encode(newPwd);

        Member modifiedMember = member.modifyPwd(encodedPwd);

        return true;
    }

    public ModifyNicknameResponse modifyNickname(String email, String nickname) {
        checkDuplicationForNickname(nickname);

        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }
        Member member = findMember.get();

        Member modifiedMember = member.modifyNickname(nickname);

        return ModifyNicknameResponse.of(modifiedMember);
    }

    public WithdrawalMemberResponse removeMember(String email, String pwd) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException();
        }
        Member member = findMember.get();

        checkCurrentPwd(pwd, member.getPwd());

        member.remove();

        return WithdrawalMemberResponse.of(member);
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

    private void checkCurrentPwd(String currentPwd, String encodedPwd) {
        boolean isMatched = passwordEncoder.matches(currentPwd, encodedPwd);
        if (!isMatched) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }
}
