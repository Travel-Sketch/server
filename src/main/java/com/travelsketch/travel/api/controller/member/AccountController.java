package com.travelsketch.travel.api.controller.member;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.member.request.CreateMemberRequest;
import com.travelsketch.travel.api.controller.member.response.CreateMemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

        return ApiResponse.created(response);
    }
}
