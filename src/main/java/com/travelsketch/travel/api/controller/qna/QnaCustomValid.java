package com.travelsketch.travel.api.controller.qna;

import static com.travelsketch.travel.api.controller.PatternUtils.isDigit;

public abstract class QnaCustomValid {

    public static void validPwd(String pwd) {
        if (pwd.length() != 4) {
            throw new IllegalArgumentException("비밀번호는 반드시 4자입니다.");
        }

        boolean isMatched = isDigit(pwd);
        if (!isMatched) {
            throw new IllegalArgumentException("비밀번호는 숫자만 입력이 가능합니다.");
        }
    }
}
