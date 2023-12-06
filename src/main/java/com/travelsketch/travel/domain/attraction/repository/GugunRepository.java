package com.travelsketch.travel.domain.attraction.repository;

import com.travelsketch.travel.domain.attraction.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Long> {
}
