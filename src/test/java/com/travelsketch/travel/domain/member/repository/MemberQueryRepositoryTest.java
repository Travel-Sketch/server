package com.travelsketch.travel.domain.member.repository;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemberQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberQueryRepository memberQueryRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("이미 등록된 이메일이 존재하지 않으면 false를 반환한다.")
    @Test
    void existEmailIsFalse() {
        //given

        //when
        boolean isExistedEmail = memberQueryRepository.existEmail("karina@naver.com");

        //then
        assertThat(isExistedEmail).isFalse();
    }

    @DisplayName("이미 등록된 이메일이 존재하면 true를 반환한다.")
    @Test
    void existEmailIsTrue() {
        //given
        Member member = savedMember();

        //when
        boolean isExistedEmail = memberQueryRepository.existEmail("karina@naver.com");

        //then
        assertThat(isExistedEmail).isTrue();
    }

    @DisplayName("이미 등록된 닉네임이 존재하지 않으면 false를 반환한다.")
    @Test
    void existNicknameIsFalse() {
        //given

        //when
        boolean isExistedNickname = memberQueryRepository.existNickname("카리나");

        //then
        assertThat(isExistedNickname).isFalse();
    }

    @DisplayName("이미 등록된 닉네임이 존재하면 true를 반환한다.")
    @Test
    void existNicknameIsTrue() {
        //given
        Member member = savedMember();

        //when
        boolean isExistedNickname = memberQueryRepository.existNickname("카리나");

        //then
        assertThat(isExistedNickname).isTrue();
    }

    @DisplayName("이메일로 회원 정보를 조회한다.")
    @Test
    void findMemberInfoByEmail() {
        //given
        Member member = savedMember();

        //when
        Optional<MemberInfo> findMemberInfo = memberQueryRepository.findMemberInfoByEmail("karina@naver.com");

        //then
        assertThat(findMemberInfo).isPresent();
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