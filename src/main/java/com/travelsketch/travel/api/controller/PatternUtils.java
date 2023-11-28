package com.travelsketch.travel.api.controller;

import static java.util.regex.Pattern.*;

public abstract class PatternUtils {

    private static final String EMAIL = "\\w+@\\w+\\.\\w+(\\.\\w+)?";
    private static final String PWD = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[\\W]).+$";
    private static final String KOREAN = "^[가-힣]*$";
    private static final String DIGIT = "^[0-9]*$";
    private static final String NICKNAME = "^[a-zA-Z가-힣0-9]*$";

    public static boolean isEmail(String text) {
        return matches(EMAIL, text);
    }

    public static boolean isPwdPattern(String text) {
        return matches(PWD, text);
    }

    public static boolean isNicknamePattern(String nickname) {
        return matches(NICKNAME, nickname);
    }

    public static boolean isKorean(String text) {
        return matches(KOREAN, text);
    }

    public static boolean isDigit(String text) {
        return matches(DIGIT, text);
    }
}
