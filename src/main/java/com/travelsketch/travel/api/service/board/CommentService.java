package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.domain.board.Comment;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.repository.CommentRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
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
    private final PostRepository postRepository;

    public CreateCommentResponse createComment(String email, Long postId, Comment parentComment, String content) {
        Member member = getMember(email);

        Post post = getPost(postId);

        Comment comment = Comment.builder()
            .content(content)
            .parentComment(parentComment)
            .post(post)
            .member(member)
            .build();

        Comment savedComment = commentRepository.save(comment);

        return CreateCommentResponse.of(savedComment);
    }

    public CreateCommentResponse createChildComment(String email, Long postId, Long parentId, String content) {
        Optional<Comment> findComment = commentRepository.findById(parentId);
        if (findComment.isEmpty()) {
            throw new NoSuchElementException("상위 댓글을 찾을 수 없습니다.");
        }

        return createComment(email, postId, findComment.get(), content);
    }

    private Post getPost(Long postId) {
        Optional<Post> findPost = postRepository.findById(postId);
        if (findPost.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 게시물입니다.");
        }
        return findPost.get();
    }

    private Member getMember(String email) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        return findMember.get();
    }

}
