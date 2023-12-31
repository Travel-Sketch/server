package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.UploadFile;
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
        Member member = getMember();

        UploadFile uploadFile = UploadFile.builder()
            .uploadFileName("original_filename.png")
            .storeFileName("stored_filename.png")
            .build();

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member, List.of(uploadFile));
        postRepository.save(post);

        //when
        SearchPostResponse response = postQueryService.searchByPostId(post.getId());

        //then
        Optional<Post> findPost = postRepository.findById(response.getPostId());
        assertThat(response.getContent()).isEqualTo("게시물 내용");
        assertThat(findPost).isPresent();
        assertThat(findPost.get().getFiles().size()).isEqualTo(1);
    }

    @DisplayName("등록되지 않은 게시물이면 예외가 발생한다.")
    @Test
    void searchUnregisteredPost() {
        assertThatThrownBy(() -> postQueryService.searchByPostId(1L))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("등록되지 않은 게시물입니다.");
    }

    @DisplayName("삭제된 게시물이면 예외가 발생한다.")
    @Test
    void searchDeletedPost() {
        //given
        Member member = getMember();

        Post post = createPost(PostCategory.FREE, "게시물 제목", "게시물 내용", member, List.of());
        post.remove();
        postRepository.save(post);

        assertThatThrownBy(() -> postQueryService.searchByPostId(post.getId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("삭제된 게시물입니다.");
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
}