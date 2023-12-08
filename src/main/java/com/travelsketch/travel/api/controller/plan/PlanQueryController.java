package com.travelsketch.travel.api.controller.plan;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanDetailResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/plans")
public class PlanQueryController {

    @GetMapping
    public ApiResponse<PageResponse<PlanResponse>> searchPlans(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PlanResponse response = PlanResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        PageImpl<PlanResponse> content = new PageImpl<>(List.of(response), pageRequest, 1);

        PageResponse<PlanResponse> result = new PageResponse<>(content);

        return ok(result);
    }

    @GetMapping("/{planId}")
    public ApiResponse<PlanDetailResponse> searchPlan(@PathVariable Long planId) {
        PlanDetailResponse response = PlanDetailResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .writer("카리나")
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }
}
