package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberQueryService memberQueryService;

    @Autowired
    private MemberRepository memberRepository;

    @DisplayName("이메일이 일치하는 회원 정보가 존재하지 않으면 예외가 발생한다.")
    @Test
    void searchMemberInfoNotMatchedEmail() {
        //given

        //when //then
        assertThatThrownBy(() -> memberQueryService.searchMemberInfo("karina@naver.com"))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("일치하는 회원 정보가 존재하지 않습니다.");
    }

    @DisplayName("이메일을 입력 받아 회원 정보를 조회할 수 있다.")
    @Test
    void searchMemberInfo() {
        //given
        Member member = savedMember();

        //when
        MemberInfo memberInfo = memberQueryService.searchMemberInfo("karina@naver.com");

        //then
        assertThat(memberInfo.getNickname()).isEqualTo("카리나");
    }

    @DisplayName("닉네임을 입력 받아 중복 확인을 할 수 있다.")
    @Test
    void isExistNickname() {
        //given
        Member member = savedMember();

        //when
        boolean isExist = memberQueryService.isExistNickname("카리나");

        //then
        assertThat(isExist).isTrue();
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