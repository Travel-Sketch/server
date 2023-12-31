package com.travelsketch.travel.domain.board.repository;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.Role;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static com.travelsketch.travel.domain.board.Post.createPost;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class PostQueryRepositoryTest extends IntegrationTestSupport {

    @Autowired
    private PostQueryRepository postQueryRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @DisplayName("페이지 번호를 입력 받아 게시물 목록을 조회한다.")
    @Test
    void findByPage() {
        //given
        Member member = getMember();

        ArrayList<Post> posts = createPosts(member);

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        List<SearchPostsResponse> responses = postQueryRepository.findByCond(pageRequest, "");

        //then
        assertThat(responses).hasSize(4)
            .extracting("postId", "title")
            .containsExactlyInAnyOrder(
                tuple(posts.get(0).getId(), "게시글1 제목"),
                tuple(posts.get(1).getId(), "게시글2 제목"),
                tuple(posts.get(2).getId(), "게시글3 제목"),
                tuple(posts.get(3).getId(), "게시글4")
            );

    }

    @DisplayName("검색 쿼리를 추가적으로 입력할 수 있다.")
    @Test
    void findByCriteria() {
        //given
        Member member = getMember();

        ArrayList<Post> posts = createPosts(member);

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        List<SearchPostsResponse> responses = postQueryRepository.findByCond(pageRequest, "제목");

        //then
        assertThat(responses).hasSize(3)
            .extracting("postId", "title")
            .containsExactlyInAnyOrder(
                tuple(posts.get(0).getId(), "게시글1 제목"),
                tuple(posts.get(1).getId(), "게시글2 제목"),
                tuple(posts.get(2).getId(), "게시글3 제목")
            );
    }

    @DisplayName("검색 조회 결과 수를 반환할 수 있다.")
    @Test
    void findCountByCond() {
        //given
        Member member = getMember();

        ArrayList<Post> posts = createPosts(member);

        PageRequest pageRequest = PageRequest.of(0, 10);

        //when
        Long count = postQueryRepository.findCountByCond("제목");

        //then
        assertThat(count).isEqualTo(3);
    }

    private Member getMember() {
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
        return member;
    }

    private ArrayList<Post> createPosts(Member member) {
        Post post1 = createPost(PostCategory.FREE, "게시글1 제목", "게시글1 내용", member, List.of());
        Post post2 = createPost(PostCategory.FREE, "게시글2 제목", "게시글2 내용", member, List.of());
        Post post3 = createPost(PostCategory.FREE, "게시글3 제목", "게시글3 내용", member, List.of());
        Post post4 = createPost(PostCategory.FREE, "게시글4", "게시글4", member, List.of());
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        postRepository.save(post4);
        return new ArrayList<>(List.of(post1, post2, post3, post4));
    }
}