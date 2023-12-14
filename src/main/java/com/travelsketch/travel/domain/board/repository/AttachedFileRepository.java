package com.travelsketch.travel.domain.board.repository;

import com.travelsketch.travel.domain.board.AttachedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachedFileRepository extends JpaRepository<AttachedFile, Long> {
    List<AttachedFile> findAllByPostId(Long post_id);
}