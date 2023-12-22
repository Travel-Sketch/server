package com.travelsketch.travel.api.controller.member.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckEmailDuplicationRequest {

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(max = 10, message = "닉네임은 최대 10자입니다.")
    private String nickname;

    @Builder
    public CheckEmailDuplicationRequest(String nickname) {
        this.nickname = nickname;
    }
}
