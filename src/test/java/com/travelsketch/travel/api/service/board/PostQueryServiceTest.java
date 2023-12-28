package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.travelsketch.travel.domain.board.Post.createPost;
import static org.assertj.core.api.Assertions.assertThat;

class PostQueryServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostQueryService postQueryService;

    @DisplayName("모든 사용자는 게시물 상세 조회를 할 수 있다.")
    @Test
    void findById() {
        //given
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

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member, List.of());
        postRepository.save(post);

        //when
        SearchPostResponse response = postQueryService.searchByPostId(post.getId());

        //then
        assertThat(response.getContent()).isEqualTo("게시물 내용");
    }
}

}