package com.travelsketch.travel.domain.qna.repository;

import com.travelsketch.travel.domain.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaRepository extends JpaRepository<Qna, Long> {
}
