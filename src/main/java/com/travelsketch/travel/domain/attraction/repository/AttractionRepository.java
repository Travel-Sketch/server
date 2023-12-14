package com.travelsketch.travel.domain.attraction.repository;

import com.travelsketch.travel.domain.attraction.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    List<Attraction> findByIdIn(List<Integer> ids);
}
