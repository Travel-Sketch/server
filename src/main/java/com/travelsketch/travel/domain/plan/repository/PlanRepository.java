package com.travelsketch.travel.domain.plan.repository;

import com.travelsketch.travel.domain.plan.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
}
