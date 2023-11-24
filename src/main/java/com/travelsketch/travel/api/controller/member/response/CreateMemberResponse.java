package com.travelsketch.travel.api.controller.member.response;

import com.travelsketch.travel.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateMemberResponse {

    private final String email;
    private final String name;
    private final String birth;
    private final String gender;
    private final String nickname;
    private final LocalDateTime joinedDate;

    @Builder
    private CreateMemberResponse(String email, String name, String birth, String gender, String nickname, LocalDateTime joinedDate) {
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.joinedDate = joinedDate;
    }

    public static CreateMemberResponse of(Member member) {
        return CreateMemberResponse.builder()
            .email(member.getEmail())
            .name(member.getName())
            .birth(member.getBirth())
            .gender(member.getGender())
            .nickname(member.getNickname())
            .joinedDate(member.getCreatedDate())
            .build();
    }
}
