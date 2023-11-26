package com.travelsketch.travel.api.controller.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyPwdRequest {

    @NotBlank(message = "현재 비밀번호는 필수입니다.")
    private String currentPwd;

    @Size(min = 8, max = 20, message = "비밀번호는 최소 8자, 최대 20자 입니다.")
    private String newPwd;

    @Builder
    private ModifyPwdRequest(String currentPwd, String newPwd) {
        this.currentPwd = currentPwd;
        this.newPwd = newPwd;
    }
}
