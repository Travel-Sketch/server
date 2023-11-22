package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyNicknameRequest {

    private String nickname;

    @Builder
    private ModifyNicknameRequest(String nickname) {
        this.nickname = nickname;
    }
}
