package com.travelsketch.travel.api.service.member.dto;

import lombok.Builder;

public record CreateMemberDto(String email, String pwd, String name, String birth, String gender, String nickname) {

    @Builder
    public CreateMemberDto {

    }
}
