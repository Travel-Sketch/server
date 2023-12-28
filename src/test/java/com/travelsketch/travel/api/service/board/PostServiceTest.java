package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.IntegrationTestSupport;
import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.api.controller.board.response.ModifyPostResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.UploadFile;
import com.travelsketch.travel.domain.board.repository.AttachedFileRepository;
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

    @Autowired
    private AttachedFileRepository attachedFileRepository;

    @DisplayName("제목, 내용, 첨부파일을 입력 받아 게시물을 등록할 수 있다.")
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

    @DisplayName("제목, 내용을 입력 받아 게시물을 수정할 수 있다.")
    @Test
    void modifyPost() {
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

        UploadFile uploadFile = UploadFile.builder()
            .uploadFileName("original_filename.png")
            .storeFileName("stored_filename.png")
            .build();

        Post post = Post.createPost(PostCategory.FREE, "게시물 제목1", "게시물 내용1", member, List.of(uploadFile));
        postRepository.save(post);

        // 새 파일
        UploadFile newFile = UploadFile.builder()
            .uploadFileName("original_filename2.png")
            .storeFileName("stored_filename2.png")
            .build();

        // when (기존 파일 삭제, 새 파일 추가)
        ModifyPostResponse response = postService.modifyPost(member.getEmail(), post.getId(), "게시물 제목", "게시물 내용 수정", List.of(newFile), List.of(post.getFiles().get(0).getId()));

        // then
        Optional<Post> findPost = postRepository.findById(response.getPostId());
        assertThat(findPost).isPresent();
        assertThat(findPost.get().getContent()).isEqualTo("게시물 내용 수정");
        assertThat(findPost.get().getFiles().size()).isEqualTo(2);  // 기존 파일 + 새로운 파일 2개
        assertThat(findPost.get().getFiles().get(0).getIsDeleted()).isEqualTo(true);
        assertThat(findPost.get().getFiles().get(1).getUploadFile().getStoreFileName()).isEqualTo("stored_filename2.png");
    }

}