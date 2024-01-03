package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.CreateCommentResponse;
import com.travelsketch.travel.domain.board.Comment;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.repository.CommentRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.travelsketch.travel.domain.board.Post.createPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommentServiceTest extends IntegrationTestSupport {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CommentRepository commentRepository;

    @DisplayName("회원은 게시물에 댓글을 등록할 수 있다.")
    @Test
    void createComment() {
        // given
        Member member1 = Member.builder()
            .email("cherry@naver.com")
            .pwd(passwordEncoder.encode("cherry_password"))
            .name("서지현")
            .birth("2010-01-01")
            .gender("F")
            .nickname("체리")
            .role(Role.USER)
            .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
            .email("apple@naver.com")
            .pwd(passwordEncoder.encode("apple_password"))
            .name("서현지")
            .birth("2011-11-11")
            .gender("F")
            .nickname("사과")
            .role(Role.USER)
            .build();
        memberRepository.save(member2);

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member1, List.of());
        postRepository.save(post);

        // when
        CreateCommentResponse response = commentService.createComment(member2.getEmail(), 1L, null, "댓글 내용");

        // then
        Optional<Comment> findComment = commentRepository.findById(response.getCommentId());
        assertThat(findComment).isPresent();
    }

    @DisplayName("입력 받은 상위 댓글 id가 유효하지 않으면 예외가 발생한다.")
    @Test
    void createCommentWithInvalidParentCommentId() {
        // given
        Member member1 = Member.builder()
            .email("cherry@naver.com")
            .pwd(passwordEncoder.encode("cherry_password"))
            .name("서지현")
            .birth("2010-01-01")
            .gender("F")
            .nickname("체리")
            .role(Role.USER)
            .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
            .email("apple@naver.com")
            .pwd(passwordEncoder.encode("apple_password"))
            .name("서현지")
            .birth("2011-11-11")
            .gender("F")
            .nickname("사과")
            .role(Role.USER)
            .build();
        memberRepository.save(member2);

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member1, List.of());
        postRepository.save(post);

        //when //then
        assertThatThrownBy(() -> commentService.createComment(member2.getEmail(), 1L, 123L, ""))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("상위 댓글을 찾을 수 없습니다.");
    }

    @DisplayName("입력 받은 게시글 id가 유효하지 않으면 예외가 발생한다.")
    @Test
    void createCommentWithInvalidPostId() {
        // given
        Member member1 = Member.builder()
            .email("cherry@naver.com")
            .pwd(passwordEncoder.encode("cherry_password"))
            .name("서지현")
            .birth("2010-01-01")
            .gender("F")
            .nickname("체리")
            .role(Role.USER)
            .build();
        memberRepository.save(member1);

        Member member2 = Member.builder()
            .email("apple@naver.com")
            .pwd(passwordEncoder.encode("apple_password"))
            .name("서현지")
            .birth("2011-11-11")
            .gender("F")
            .nickname("사과")
            .role(Role.USER)
            .build();
        memberRepository.save(member2);

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member1, List.of());
        postRepository.save(post);

        //when //then
        assertThatThrownBy(() -> commentService.createComment(member2.getEmail(), 123L, null, ""))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 게시물입니다.");
    }

}