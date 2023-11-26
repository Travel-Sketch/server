package com.travelsketch.travel.api.controller.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawalMemberRequest {

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String pwd;

    @Builder
    private WithdrawalMemberRequest(String pwd) {
        this.pwd = pwd;
    }
}
