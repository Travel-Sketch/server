package com.travelsketch.travel.api.service.plan;

import com.travelsketch.travel.api.controller.plan.response.CreatePlanResponse;
import com.travelsketch.travel.api.controller.plan.response.ModifyPlanResponse;
import com.travelsketch.travel.api.controller.plan.response.RemovePlanResponse;
import com.travelsketch.travel.domain.attraction.Attraction;
import com.travelsketch.travel.domain.attraction.repository.AttractionRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import com.travelsketch.travel.domain.plan.Plan;
import com.travelsketch.travel.domain.plan.repository.PlanRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PlanService {

    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;
    private final AttractionRepository attractionRepository;

    public CreatePlanResponse createPlan(String email, String title, List<Integer> attractionIds) {
        Member member = getMember(email);

        List<Attraction> attractions = attractionRepository.findByIdIn(attractionIds);

        if (attractionIds.size() != attractions.size()) {
            throw new NoSuchElementException("존재하지 않는 관광지 정보입니다.");
        }

        Plan plan = Plan.createPlan(title, member, attractions);

        Plan savedPlan = planRepository.save(plan);

        return CreatePlanResponse.of(savedPlan);
    }

    public ModifyPlanResponse modifyPlan(Long planId, String title, List<Integer> attractionIds) {
        Optional<Plan> findPlan = planRepository.findById(planId);
        if (findPlan.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 계획입니다.");
        }
        Plan plan = findPlan.get();

        List<Attraction> attractions = attractionRepository.findByIdIn(attractionIds);

        if (attractionIds.size() != attractions.size()) {
            throw new NoSuchElementException("존재하지 않는 관광지 정보입니다.");
        }

        Plan modifiedPlan = plan.modifyPlan(title, attractions);

        return ModifyPlanResponse.of(modifiedPlan);
    }

    public RemovePlanResponse removePlan(Long planId) {
        Optional<Plan> findPlan = planRepository.findById(planId);
        if (findPlan.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 계획입니다.");
        }
        Plan plan = findPlan.get();

        plan.remove();

        return RemovePlanResponse.of(plan);
    }

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }
}
