package com.travelsketch.travel.api.controller.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberCustomValidTest {

    @DisplayName("이메일 형식이 맞지 않으면 예외가 발생한다.")
    @Test
    void validEmailPattern() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validEmail("karina@naver"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("올바른 이메일 형식이 아닙니다.");
    }

    @DisplayName("지원하지 않는 도메인이라면 예외가 발생한다.")
    @Test
    void validEmailDomain() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validEmail("karina@daum.net"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("지원하지 않는 도메인입니다.");
    }

    @DisplayName("비밀번호 형식이 맞지 않으면 예외가 발생한다.")
    @Test
    void validPwd() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validPwd("karina1234"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 영문, 숫자, 특수문자가 모두 포함되어야 합니다.");
    }

    @DisplayName("이름에 한글 이외의 문자가 입력이 되면 예외가 발생한다.")
    @Test
    void validName() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validName("karina"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 한글만 입력이 가능합니다.");
    }

    @DisplayName("성별에 M, F 이외의 문자가 입력이 되면 예외가 발생한다.")
    @Test
    void validGender() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validGender("W"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("성별은 M, F만 입력이 가능합니다.");
    }

    @DisplayName("닉네임 형식이 맞지 않으면 예외가 발생한다.")
    @Test
    void validNickname() {
        //given //when //then
        assertThatThrownBy(() -> MemberCustomValid.validNickname("카리나!"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("닉네임은 한글, 영어 대소문자, 숫자만 입력이 가능합니다.");
    }
}