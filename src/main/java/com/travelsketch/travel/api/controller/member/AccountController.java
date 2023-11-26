package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.request.AuthenticationNumberRequest;
import com.travelsketch.travel.api.controller.member.request.CheckAuthenticationNumberRequest;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
import com.travelsketch.travel.api.controller.member.request.LoginMemberRequest;
import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.controller.member.response.TokenInfo;
import com.travelsketch.travel.api.service.member.AccountService;
import com.travelsketch.travel.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.created;
import static com.travelsketch.travel.api.ApiResponse.ok;
import static com.travelsketch.travel.api.controller.member.MemberCustomValid.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AccountController {

    private final MemberService memberService;
    private final AccountService accountService;

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateMemberResponse> join(@Valid @RequestBody CreateMemberRequest request) {

        validEmail(request.getEmail());
        validPwd(request.getPwd());
        validName(request.getName());
        validGender(request.getGender());
        validNickname(request.getNickname());

        CreateMemberResponse response = memberService.createMember(request.toDto());

        return created(response);
    }

    @PostMapping("/login")
    public ApiResponse<TokenInfo> login(@Valid @RequestBody LoginMemberRequest request) {

        TokenInfo tokenInfo = accountService.login(request.getEmail(), request.getPwd());

        return ok(tokenInfo);
    }

    @PostMapping("/auth")
    public ApiResponse<String> requestAuthenticationNumber(@RequestBody AuthenticationNumberRequest request) {
        return ok(null);
    }

    @PostMapping("/auth/check")
    public ApiResponse<String> checkAuthenticationNumber(@RequestBody CheckAuthenticationNumberRequest request) {
        return ok(null);
    }
}
