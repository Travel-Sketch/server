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

/**
 * 회원 서비스
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberQueryRepository memberQueryRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원 정보를 입력 받아 등록을 하여 등록된 결과를 반환한다.
     *
     * @param dto 회원 정보
     * @return 등록된 회원의 정보
     */
    public CreateMemberResponse createMember(CreateMemberDto dto) {

        checkDuplicationForEmail(dto.email());
        checkDuplicationForNickname(dto.nickname());

        Member member = toEntity(dto);

        Member savedMember = memberRepository.save(member);

        return CreateMemberResponse.of(savedMember);
    }

    /**
     * 이메일, 현재 비밀번호, 새로운 비밀번호를 입력 받아 비밀번호를 수정하고 true를 반환한다.
     *
     * @param email      수정할 계정의 이메일
     * @param currentPwd 현재 비밀번호
     * @param newPwd     새로운 비밀번호
     * @return 변경에 성공하면 true를 반환
     */
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

    /**
     * 이메일, 새로운 닉네임을 입력 받아 닉네임을 수정하고 수정된 결과를 반환한다.
     *
     * @param email    수정할 계정의 이메일
     * @param nickname 새로운 닉네임
     * @return 수정된 회원의 정보
     */
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

    /**
     * 이메일, 현재 비밀번호를 입력 받아 삭제하고 삭제된 결과를 반환한다.
     *
     * @param email 삭제할 계정의 이메일
     * @param pwd   계정의 현재 비밀번호
     * @return 삭제된 회원의 정보
     */
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

    /**
     * 이메일 중복 확인을 한다.
     *
     * @param email 중복 확인할 이메일
     * @throws IllegalArgumentException 중복된 이메일이라면 예외가 발생
     */
    private void checkDuplicationForEmail(String email) {
        boolean isExistedEmail = memberQueryRepository.existEmail(email);
        if (isExistedEmail) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }
    }

    /**
     * 닉네임 중복 확인을 한다.
     *
     * @param nickname 중복 확인할 닉네임
     * @throws IllegalArgumentException 중복된 닉네임이라면 예외가 발생
     */
    private void checkDuplicationForNickname(String nickname) {
        boolean isExistedNickname = memberQueryRepository.existNickname(nickname);
        if (isExistedNickname) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임 입니다.");
        }
    }

    /**
     * 회원 엔티티를 생성하여 반환환다.
     *
     * @param dto 회원 정보
     * @return 생성된 회원 엔티티
     */
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

    /**
     * 현재 비밀번호가 일치하는지 확인한다.
     *
     * @param currentPwd 현재 비밀번호
     * @param encodedPwd 암호화된 비밀번호
     */
    private void checkCurrentPwd(String currentPwd, String encodedPwd) {
        boolean isMatched = passwordEncoder.matches(currentPwd, encodedPwd);
        if (!isMatched) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }
}
