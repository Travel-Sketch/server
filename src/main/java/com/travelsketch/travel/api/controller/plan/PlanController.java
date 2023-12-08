package com.travelsketch.travel.api.controller.plan;

import com.travelsketch.travel.api.ApiResponse;
import com.travelsketch.travel.api.controller.plan.request.CreatePlanRequest;
import com.travelsketch.travel.api.controller.plan.request.ModifyPlanRequest;
import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.api.controller.plan.response.ModifyPlanResponse;
import com.travelsketch.travel.api.controller.plan.response.RemovePlanResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static com.travelsketch.travel.api.ApiResponse.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/plans")
public class PlanController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CreatePlanResponse> createPlan(@RequestBody CreatePlanRequest request) {
        CreatePlanResponse response = CreatePlanResponse.builder()
            .planId(1L)
            .title("나의 여행 계획 제목")
            .attractionCount(3)
            .createdDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return created(response);
    }

    @PatchMapping("/{planId}")
    public ApiResponse<ModifyPlanResponse> modifyPlan(
        @PathVariable Long planId,
        @RequestBody ModifyPlanRequest request
    ) {
        ModifyPlanResponse response = ModifyPlanResponse.builder()
            .planId(1L)
            .title("수정된 여행 계획 제목")
            .attractionCount(3)
            .modifiedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }

    @DeleteMapping("/{planId}")
    public ApiResponse<RemovePlanResponse> removePlan(@PathVariable Long planId) {
        RemovePlanResponse response = RemovePlanResponse.builder()
            .planId(1L)
            .title("삭제된 여행 계획 제목")
            .removedDate(LocalDateTime.of(2023, 12, 8, 14, 52))
            .build();

        return ok(response);
    }
}
