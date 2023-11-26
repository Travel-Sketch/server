package com.travelsketch.travel.api.controller.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginMemberRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String pwd;

    @Builder
    private LoginMemberRequest(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
