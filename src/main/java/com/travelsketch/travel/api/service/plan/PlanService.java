package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.domain.plan.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {

    private final PlanRepository planRepository;

    public CreatePlanResponse createPlan(String email, String title, List<Integer> attractionIds) {
        return null;
    }
}
