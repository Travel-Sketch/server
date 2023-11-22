package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginMemberRequest {

    private String email;
    private String pwd;

    @Builder
    private LoginMemberRequest(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
