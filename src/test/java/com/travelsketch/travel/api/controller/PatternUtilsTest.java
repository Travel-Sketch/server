package com.travelsketch.travel.api.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class PatternUtilsTest {

    @DisplayName("문자열이 이메일 형식이라면 True를 반환한다.")
    @Test
    void isEmail() {
        //given //when
        boolean result = PatternUtils.isEmail("karina@naver.com");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("문자열이 이메일 형식이 아니라면 False를 반환한다.")
    @ParameterizedTest
    @CsvSource({"karina", "karina@", "naver.com", "@naver.com", "karina@naver", "karina@navercom"})
    void isNotEmail() {
        //given //when
        boolean result = PatternUtils.isEmail("karina");

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("문자열이 비밀번호 패턴이라면 True를 반환한다.")
    @Test
    void isPwdPattern() {
        //given //when
        boolean result = PatternUtils.isPwdPattern("karina1234!");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("문자열이 비밀번호 패턴이 아니라면 False를 반환한다.")
    @ParameterizedTest
    @CsvSource({"asepakarina", "asepa karina", "karina1234", "karina!@"})
    void isNotPwdPattern(String text) {
        //given //when
        boolean result = PatternUtils.isPwdPattern(text);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("문자열이 한글로만 구성되어 있으면 True를 반환한다.")
    @Test
    void isKorean() {
        //given //when
        boolean result = PatternUtils.isKorean("유지민");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("문자열에 한글 외의 문자가 포함되어 있으면 False를 반환한다.")
    @ParameterizedTest
    @CsvSource({"유지ㅣ민", "유지민!", "Karina", "Karina 유지민"})
    void isNotKorean(String text) {
        //given //when
        boolean result = PatternUtils.isKorean(text);

        //then
        assertThat(result).isFalse();
    }

    @DisplayName("문자열이 닉네임 형식이라면 True를 반환한다.")
    @Test
    void isNicknamePattern() {
        //given //when
        boolean result = PatternUtils.isNicknamePattern("에스파karina");

        //then
        assertThat(result).isTrue();
    }

    @DisplayName("문자열이 닉네임 형식이 아니라면 False를 반환한다.")
    @ParameterizedTest
    @CsvSource({"에스파 카리나", "에스파karina!"})
    void isNotKNicknamePattern(String text) {
        //given //when
        boolean result = PatternUtils.isNicknamePattern(text);

        //then
        assertThat(result).isFalse();
    }
}