package com.travelsketch.travel.api.controller.member.response;

import com.travelsketch.travel.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WithdrawalMemberResponse {

    private final String email;
    private final String name;
    private final LocalDateTime removedDate;

    @Builder
    private WithdrawalMemberResponse(String email, String name, LocalDateTime removedDate) {
        this.email = email;
        this.name = name;
        this.removedDate = removedDate;
    }

    public static WithdrawalMemberResponse of(Member member) {
        return WithdrawalMemberResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .removedDate(member.getLastModifiedDate())
            .build();
    }
}
