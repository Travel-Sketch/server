package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.request.*;
import com.travelsketch.travel.api.controller.member.response.CheckEmailDuplicationResponse;
import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import com.travelsketch.travel.api.controller.member.response.TokenInfo;
import com.travelsketch.travel.api.service.member.AccountService;
import com.travelsketch.travel.api.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.travelsketch.travel.api.ApiResponse.*;
import static com.travelsketch.travel.api.controller.member.MemberCustomValid.*;

/**
 * 토큰 없이 사용 가능한 회원 API
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AccountController {

    private final MemberService memberService;
    private final AccountService accountService;

    /**
     * 회원 가입 API
     *
     * @param request 이메일, 비밀번호, 이름, 생년월일, 성별, 닉네임 정보
     * @return 가입된 회원의 정보
     */
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

    /**
     * 로그인 API
     *
     * @param request 이메일, 비밀번호 정보
     * @return 로그인된 회원에게 발급된 토큰 정보
     */
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

    @PostMapping("/check/email")
    public ApiResponse<CheckEmailDuplicationResponse> checkEmailDuplication(@RequestBody CheckEmailDuplicationRequest request) {
        CheckEmailDuplicationResponse response = CheckEmailDuplicationResponse.builder()
            .isUsed(true)
            .build();

        return ok(response);
    }
}
