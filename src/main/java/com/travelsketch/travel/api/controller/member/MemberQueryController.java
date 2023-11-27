package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.api.service.member.MemberQueryService;
import com.travelsketch.travel.security.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 회원 조회용 API
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/members")
public class MemberQueryController {

    private final MemberQueryService memberQueryService;
    private final SecurityUtils securityUtils;

    /**
     * 회원 정보 조회 API
     *
     * @return 조회된 회원 정보
     */
    @GetMapping
    public ApiResponse<MemberInfo> searchMemberInfo() {
        String email = securityUtils.getCurrentEmail();

        MemberInfo memberInfo = memberQueryService.searchMemberInfo(email);

        return ApiResponse.ok(memberInfo);
    }
}
