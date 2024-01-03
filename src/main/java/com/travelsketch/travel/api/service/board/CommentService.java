package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.domain.board.Comment;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.repository.CommentRepository;
import com.travelsketch.travel.domain.board.repository.PostQueryRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostQueryRepository postQueryRepository;

    public CreateCommentResponse createComment(String email, Long postId, Long parentCommentId, String content) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        Member member = findMember.get();

        Optional<Post> findPost = postQueryRepository.findByIdWithMember(postId);
        if (findPost.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 게시물입니다.");
        }
        Post post = findPost.get();

        Comment parentComment;
        if (parentCommentId != null) {
            Optional<Comment> findParentComment = commentRepository.findById(parentCommentId);
            if (findParentComment.isPresent()) {
                parentComment = findParentComment.get();
            } else {
                throw new NoSuchElementException("상위 댓글을 찾을 수 없습니다.");
            }
        } else {
            parentComment = null;
        }

        Comment comment = Comment.builder()
            .content(content)
            .parentComment(parentComment)
            .post(post)
            .member(member)
            .build();

        Comment savedComment = commentRepository.save(comment);

        return CreateCommentResponse.of(savedComment);
    }

}
