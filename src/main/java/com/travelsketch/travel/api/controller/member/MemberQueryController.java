package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
import com.travelsketch.travel.api.service.member.MemberQueryService;
import com.travelsketch.travel.security.SecurityUtil;
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

    @GetMapping
    public ApiResponse<MemberInfo> searchMemberInfo() {
        String email = SecurityUtil.getCurrentEmail();

        MemberInfo memberInfo = memberQueryService.searchMemberInfo(email);

        return ApiResponse.ok(memberInfo);
    }
}
