package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.request.AuthenticationNumberRequest;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
import com.travelsketch.travel.api.controller.member.request.LoginMemberRequest;
import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.controller.member.response.TokenInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AccountController {

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreateMemberResponse> join(@RequestBody CreateMemberRequest request) {
        CreateMemberResponse response = CreateMemberResponse.builder()
            .email("temp@naver.com")
            .name("유지민")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .joinedDate(LocalDateTime.of(2023, 11, 11, 18, 0))
            .build();

        return created(response);
    }

    @PostMapping("/login")
    public ApiResponse<TokenInfo> login(@RequestBody LoginMemberRequest request) {
        TokenInfo tokenInfo = TokenInfo.builder()
            .grantType("Bearer")
            .accessToken("access.token")
            .refreshToken("refresh.token")
            .build();
        return ok(tokenInfo);
    }

    @PostMapping("/auth")
    public ApiResponse<String> requestAuthenticationNumber(@RequestBody AuthenticationNumberRequest request) {
        return ok(null);
    }
}
