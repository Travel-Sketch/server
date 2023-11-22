package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.response.MemberInfo;
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

    @GetMapping
    public ApiResponse<MemberInfo> searchMemberInfo() {
        MemberInfo memberInfo = MemberInfo.builder()
            .email("temp@naver.com")
            .name("temp1234!")
            .birth("2000-04-11")
            .gender("F")
            .nickname("카리나")
            .build();
        return ApiResponse.ok(memberInfo);
    }
}
