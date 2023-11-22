package com.travelsketch.travel.api.controller.member.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuthenticationNumberRequest {

    private String email;

    @Builder
    private AuthenticationNumberRequest(String email) {
        this.email = email;
    }
}
