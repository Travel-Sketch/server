package com.travelsketch.travel.api.controller.member.response;

import com.travelsketch.travel.domain.member.Member;
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

    public static ModifyNicknameResponse of(Member member) {
        return ModifyNicknameResponse.builder()
            .modifiedNickname(member.getNickname())
            .modifiedDate(member.getLastModifiedDate())
            .build();
    }
}
