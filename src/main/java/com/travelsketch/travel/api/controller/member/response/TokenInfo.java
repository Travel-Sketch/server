package com.travelsketch.travel.api.controller.member.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenInfo {

    private final String grantType;
    private final String accessToken;
    private final String refreshToken;

    @Builder
    private TokenInfo(String grantType, String accessToken, String refreshToken) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
