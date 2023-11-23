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
            .email("temp@naver.com")
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
            .email("temp@naver.com")
            .pwd("temp1234!")
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

    private Member savedMember() {
        Member member = Member.builder()
            .email("temp@naver.com")
            .pwd(passwordEncoder.encode("temp1234!"))
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .role(Role.USER)
            .build();
        return memberRepository.save(member);
    }
}