package com.travelsketch.travel.api.controller.qna;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QnaCustomValidTest {

    @DisplayName("비밀번호에 숫자 이외의 문자가 입력이 되면 예외가 발생한다.")
    @Test
    void validPwdPattern() {
        //given //when //then
        assertThatThrownBy(() -> QnaCustomValid.validPwd("321!"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 숫자만 입력이 가능합니다.");
    }

    @DisplayName("비밀번호의 길이가 4자가 아니라면 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource({"12345", "123"})
    void validPwdLength(String text) {
        //given //when //then
        assertThatThrownBy(() -> QnaCustomValid.validPwd(text))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("비밀번호는 반드시 4자입니다.");
    }
}