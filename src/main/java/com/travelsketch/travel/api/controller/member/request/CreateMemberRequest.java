package com.travelsketch.travel.api.controller.member.request;

import com.travelsketch.travel.api.service.member.dto.CreateMemberDto;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CreateMemberRequest {

    @NotBlank(message = "이메일은 필수입니다.")
    @Size(max = 100, message = "이메일의 길이는 최대 100자 입니다.")
    private String email;

    @Size(min = 8, max = 20, message = "비밀번호는 최소 8자, 최대 20자 입니다.")
    private String pwd;

    @NotBlank(message = "이름은 필수입니다.")
    @Size(max = 10, message = "이름의 길이는 최대 10자 입니다.")
    private String name;

    @NotNull(message = "생년월일은 필수입니다.")
    @Past(message = "생년월일을 확인해주세요.")
    private LocalDate birth;

    @Size(min = 1, max = 1, message = "성별은 반드시 1자입니다.")
    private String gender;

    @NotBlank(message = "닉네임은 필수입니다.")
    @Size(max = 10, message = "닉네임은 최대 10자 입니다.")
    private String nickname;

    @Builder
    private CreateMemberRequest(String email, String pwd, String name, LocalDate birth, String gender, String nickname) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
    }

    public CreateMemberDto toDto() {
        return CreateMemberDto.builder()
            .email(this.email)
            .pwd(this.pwd)
            .name(this.name)
            .birth(this.birth.toString())
            .gender(this.gender)
            .nickname(this.nickname)
            .build();
    }

}
