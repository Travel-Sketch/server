package com.travelsketch.travel.domain.board.repository;

import com.travelsketch.travel.domain.board.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
