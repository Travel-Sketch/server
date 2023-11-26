package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.service.member.dto.CreateMemberDto;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("입력 받은 이메일이 이미 사용 중이라면 예외가 발생한다.")
    @Test
    void createMemberDuplicateEmail() {
        //given
        CreateMemberDto dto = CreateMemberDto.builder()
            .email("karina@naver.com")
            .pwd("winter1234!")
            .name("김민정")
            .birth("2001-01-01")
            .gender("F")
            .nickname("윈터")
            .build();

        Member member = savedMember();

        //when //then
        assertThatThrownBy(() -> memberService.createMember(dto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 사용 중인 이메일 입니다.");
    }

    @DisplayName("입력 받은 닉네임이 이미 사용 중이라면 예외가 발생한다.")
    @Test
    void createMemberDuplicateNickname() {
        //given
        CreateMemberDto dto = CreateMemberDto.builder()
            .email("winter@naver.com")
            .pwd("winter1234!")
            .name("김민정")
            .birth("2001-01-01")
            .gender("F")
            .nickname("카리나")
            .build();

        Member member = savedMember();

        //when //then
        assertThatThrownBy(() -> memberService.createMember(dto))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이미 사용 중인 닉네임 입니다.");
    }

    @DisplayName("이메일, 비밀번호, 이름, 생년월일, 성별, 닉네임을 입력 받아 회원을 등록 한다.")
    @Test
    void createMember() {
        //given
        CreateMemberDto dto = CreateMemberDto.builder()
            .email("karina@naver.com")
            .pwd("karina1234!")
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .build();

        //when
        CreateMemberResponse response = memberService.createMember(dto);

        //then
        assertThat(response.getNickname()).isEqualTo("카리나");
    }

    @DisplayName("입력 받은 현재 비밀번호가 일치하지 않으면 예외가 발생한다.")
    @Test
    void modifyPwdNotMatchCurrentPwd() {
        //given
        Member member = savedMember();

        //when //then
        assertThatThrownBy(() -> memberService.modifyPwd("karina@naver.com", "winter1234!", "karina5678@"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("현재 비밀번호가 일치하지 않습니다.");
    }

    @DisplayName("이메일, 현재 비밀번호, 새로운 비밀번호를 입력 받아 비밀번호 수정을 한다.")
    @Test
    void modifyPwd() {
        //given
        Member member = savedMember();

        //when
        boolean result = memberService.modifyPwd("karina@naver.com", "karina1234!", "karina5678@");

        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember).isPresent();

        boolean matches = passwordEncoder.matches("karina5678@", findMember.get().getPwd());
        assertThat(matches).isTrue();
    }

    private Member savedMember() {
        Member member = Member.builder()
            .email("karina@naver.com")
            .pwd(passwordEncoder.encode("karina1234!"))
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .role(Role.USER)
            .build();
        return memberRepository.save(member);
    }
}