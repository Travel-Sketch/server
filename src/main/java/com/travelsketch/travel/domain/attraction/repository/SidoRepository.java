package com.travelsketch.travel.domain.attraction.repository;

import com.travelsketch.travel.domain.attraction.Sido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SidoRepository extends JpaRepository<Sido, Long> {
}
