package com.travelsketch.travel.api.service.member;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.member.response.TokenInfo;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest extends IntegrationTestSupport {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("입력 받은 이메일과 비밀번호로 계정 로그인을 할 수 있다.")
    @Test
    void login() {
        //given
        Member member = saveMember();

        //when
        TokenInfo tokenInfo = accountService.login("karina@naver.com", "karina1234!");

        //then
        assertThat(tokenInfo).isNotNull();
    }

    private Member saveMember() {
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