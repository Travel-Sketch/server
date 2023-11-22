package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    private String email;
    private String pwd;
    private String name;
    private String birth;
    private String gender;
    private String nickname;

    @Builder
    private CreateMemberRequest(String email, String pwd, String name, String birth, String gender, String nickname) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }
}
