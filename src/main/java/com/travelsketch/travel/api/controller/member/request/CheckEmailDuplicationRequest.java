package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckEmailDuplicationRequest {

    private String nickname;

    @Builder
    public CheckEmailDuplicationRequest(String nickname) {
        this.nickname = nickname;
    }
}
