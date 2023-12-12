package com.travelsketch.travel.api.controller.plan;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanDetailResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import com.travelsketch.travel.api.service.plan.PlanQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.ok;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/plans")
public class PlanQueryController {

    private final PlanQueryService planQueryService;

    @GetMapping
    public ApiResponse<PageResponse<PlanResponse>> searchPlans(
        @RequestParam(defaultValue = "1") Integer page,
        @RequestParam(defaultValue = "") String query
    ) {
        if (isNegativeOrZero(page)) {
            throw new IllegalArgumentException("페이지는 1이상입니다.");
        }

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        PageResponse<PlanResponse> response = planQueryService.searchByCond(query, pageRequest);

        return ok(response);
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

    private boolean isNegativeOrZero(int number) {
        return number <= 0;
    }
}
