package com.travelsketch.travel.domain.attraction.repository;

import com.travelsketch.travel.domain.attraction.Gugun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GugunRepository extends JpaRepository<Gugun, Integer> {

    @Query("select g from Gugun g where g.sido.id=:sidoId")
    List<Gugun> findBySidoId(@Param("sidoId") Integer sidoId);
}
