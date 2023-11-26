package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.request.ModifyNicknameRequest;
import com.travelsketch.travel.api.controller.member.request.ModifyPwdRequest;
import com.travelsketch.travel.api.controller.member.request.WithdrawalMemberRequest;
import com.travelsketch.travel.api.controller.member.response.ModifyNicknameResponse;
import com.travelsketch.travel.api.controller.member.response.WithdrawalMemberResponse;
import com.travelsketch.travel.api.service.member.MemberService;
import com.travelsketch.travel.security.SecurityUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;
import static com.travelsketch.travel.api.controller.member.MemberCustomValid.validPwd;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final SecurityUtils securityUtils;

    @PatchMapping("/pwd")
    public ApiResponse<Boolean> modifyPwd(@Valid @RequestBody ModifyPwdRequest request) {
        validPwd(request.getNewPwd());

        String email = securityUtils.getCurrentEmail();

        boolean result = memberService.modifyPwd(email, request.getCurrentPwd(), request.getNewPwd());

        return ok(result);
    }

    @PatchMapping("/nickname")
    public ApiResponse<ModifyNicknameResponse> modifyNickname(@RequestBody ModifyNicknameRequest request) {
        ModifyNicknameResponse response = ModifyNicknameResponse.builder()
            .modifiedNickname("에스파 카리나")
            .modifiedDate(LocalDateTime.of(2023, 11, 11, 10, 0))
            .build();
        return ok(response);
    }

    @PatchMapping("/withdrawal")
    public ApiResponse<WithdrawalMemberResponse> withdrawal(@RequestBody WithdrawalMemberRequest request) {
        WithdrawalMemberResponse response = WithdrawalMemberResponse.builder()
            .email("temp@naver.com")
            .name("유지민")
            .removedDate(LocalDateTime.of(2023, 11, 22, 20, 0))
            .build();
        return ok(response);
    }
}
