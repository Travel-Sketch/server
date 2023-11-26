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

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/members")
public class MemberQueryController {

    private final MemberQueryService memberQueryService;
    private final SecurityUtils securityUtils;

    @GetMapping
    public ApiResponse<MemberInfo> searchMemberInfo() {
        String email = securityUtils.getCurrentEmail();

        MemberInfo memberInfo = memberQueryService.searchMemberInfo(email);

        return ApiResponse.ok(memberInfo);
    }
}
