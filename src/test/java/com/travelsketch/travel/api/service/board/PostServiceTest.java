package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class PostServiceTest extends IntegrationTestSupport {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberRepository memberRepository;


    @DisplayName("제목, 내용을 입력 받아 게시물을 등록할 수 있다.")
    @Test
    void createPost() {
        // given
        Member member = Member.builder()
            .email("cherry@naver.com")
            .pwd(passwordEncoder.encode("cherry_password"))
            .name("서지현")
            .birth("2000-01-01")
            .gender("F")
            .nickname("체리")
            .role(Role.USER)
            .build();
        memberRepository.save(member);

        // when
        CreatePostResponse response = postService.createPost(member.getEmail(), "게시물 제목", "게시물 내용", List.of());

        // then (post 등록 확인)
        Optional<Post> findPost = postRepository.findById(response.getPostId());
        assertThat(findPost).isPresent();
    }

}