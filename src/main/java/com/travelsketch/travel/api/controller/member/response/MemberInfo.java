package com.travelsketch.travel.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfo {

    private final String email;
    private final String name;
    private final String birth;
    private final String gender;
    private final String nickname;

    @Builder
    public MemberInfo(String email, String name, String birth, String gender, String nickname) {
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }
}
