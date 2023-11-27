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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.travelsketch.travel.api.ApiResponse.ok;
import static com.travelsketch.travel.api.controller.member.MemberCustomValid.validNickname;
import static com.travelsketch.travel.api.controller.member.MemberCustomValid.validPwd;

/**
 * 회원 API 컨트롤러
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final SecurityUtils securityUtils;

    /**
     * 계정 비밀번호 변경 API
     *
     * @param request 현재 비밀번호, 새로운 비밀번호 정보
     * @return 정상적으로 수정이 되었다면 true를 반환
     */
    @PatchMapping("/pwd")
    public ApiResponse<Boolean> modifyPwd(@Valid @RequestBody ModifyPwdRequest request) {
        validPwd(request.getNewPwd());

        String email = securityUtils.getCurrentEmail();

        boolean result = memberService.modifyPwd(email, request.getCurrentPwd(), request.getNewPwd());

        return ok(result);
    }

    /**
     * 닉네임 변경 API
     *
     * @param request 새로운 닉네임 정보
     * @return 변경된 회원 정보
     */
    @PatchMapping("/nickname")
    public ApiResponse<ModifyNicknameResponse> modifyNickname(@Valid @RequestBody ModifyNicknameRequest request) {
        validNickname(request.getNickname());

        String email = securityUtils.getCurrentEmail();

        ModifyNicknameResponse response = memberService.modifyNickname(email, request.getNickname());

        return ok(response);
    }

    /**
     * 회원 탈퇴 API
     *
     * @param request 현재 비밀번호 정보
     * @return 탈퇴한 회원의 정보
     */
    @PatchMapping("/withdrawal")
    public ApiResponse<WithdrawalMemberResponse> withdrawal(@Valid @RequestBody WithdrawalMemberRequest request) {
        String email = securityUtils.getCurrentEmail();

        WithdrawalMemberResponse response = memberService.removeMember(email, request.getPwd());

        return ok(response);
    }
}
