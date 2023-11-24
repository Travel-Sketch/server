package com.travelsketch.travel.api.controller.member;

import static com.travelsketch.travel.api.controller.PatternUtils.*;

public abstract class MemberCustomValid {

    public static void validEmail(String email) {
        boolean isMatched = isEmail(email);
        if (!isMatched) {
            throw new IllegalArgumentException("올바른 이메일 형식이 아닙니다.");
        }

        String domain = email.split("@")[1];

        if (!DomainType.isContainDomain(domain)) {
            throw new IllegalArgumentException("지원하지 않는 도메인입니다.");
        }
    }

    public static void validPwd(String pwd) {
        boolean isMatch = isPwdPattern(pwd);
        if (!isMatch) {
            throw new IllegalArgumentException("비밀번호는 영문, 숫자, 특수문자가 모두 포함되어야 합니다.");
        }
    }

    public static void validName(String name) {
        boolean isMatch = isKorean(name);
        if (!isMatch) {
            throw new IllegalArgumentException("이름은 한글만 입력이 가능합니다.");
        }
    }

    public static void validGender(String gender) {
        if (!gender.equals("M")
            && !gender.equals("F")) {
            throw new IllegalArgumentException("성별은 M, F만 입력이 가능합니다.");
        }
    }

    public static void validNickname(String nickname) {
        boolean isMatch = isNicknamePattern(nickname);
        if (!isMatch) {
            throw new IllegalArgumentException("닉네임은 한글, 영어 대소문자, 숫자만 입력이 가능합니다.");
        }
    }
}
