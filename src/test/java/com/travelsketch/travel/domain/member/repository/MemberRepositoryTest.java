package com.travelsketch.travel.domain.member.repository;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("이메일로 회원 엔티티를 조회한다.")
    @Test
    void findByEmail() {
        //given
        Member member = saveMember();

        //when
        Optional<Member> findMember = memberRepository.findByEmail("karina@naver.com");

        //then
        assertThat(findMember).isPresent();
    }

    private Member saveMember() {
        Member member = Member.builder()
            .email("karina@naver.com")
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