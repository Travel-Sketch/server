package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanDetailResponse;
import com.travelsketch.travel.api.controller.plan.response.PlanResponse;
import com.travelsketch.travel.domain.plan.repository.PlanQueryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanQueryService {

    private final PlanQueryRepository planQueryRepository;

    public PageResponse<PlanResponse> searchByCond(String query, Pageable pageable) {
        List<PlanResponse> content = planQueryRepository.findByCond(query, pageable);

        int count = planQueryRepository.findCountByCond(query);

        PageImpl<PlanResponse> result = new PageImpl<>(content, pageable, count);

        return new PageResponse<>(result);
    }

    public PlanDetailResponse searchPlan(Long planId) {
        return null;
    }
}
