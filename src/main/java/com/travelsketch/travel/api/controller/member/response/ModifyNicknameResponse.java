package com.travelsketch.travel.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ModifyNicknameResponse {

    private final String modifiedNickname;
    private final LocalDateTime modifiedDate;

    @Builder
    private ModifyNicknameResponse(String modifiedNickname, LocalDateTime modifiedDate) {
        this.modifiedNickname = modifiedNickname;
        this.modifiedDate = modifiedDate;
    }
}
