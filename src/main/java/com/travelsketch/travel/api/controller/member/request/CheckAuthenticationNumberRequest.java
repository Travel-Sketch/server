package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckAuthenticationNumberRequest {

    private String email;
    private String authenticationNumber;

    @Builder
    private CheckAuthenticationNumberRequest(String email, String authenticationNumber) {
        this.email = email;
        this.authenticationNumber = authenticationNumber;
    }
}
