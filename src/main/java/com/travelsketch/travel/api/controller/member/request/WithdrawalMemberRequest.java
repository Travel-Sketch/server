package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WithdrawalMemberRequest {

    private String pwd;

    @Builder
    private WithdrawalMemberRequest(String pwd) {
        this.pwd = pwd;
    }
}
