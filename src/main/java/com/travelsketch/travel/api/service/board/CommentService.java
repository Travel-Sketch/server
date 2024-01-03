package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    public CreateCommentResponse createComment(String email, Long postId, Long parentCommentId, String content) {
        return null;
    }

}
