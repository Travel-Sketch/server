package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.api.controller.board.response.ModifyPostResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.UploadFile;
import com.travelsketch.travel.domain.board.repository.PostQueryRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import com.travelsketch.travel.domain.member.Member;
import com.travelsketch.travel.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostQueryRepository postQueryRepository;

    public CreatePostResponse createPost(String email, String title, String content, List<UploadFile> files) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        Member member = findMember.get();

        Post post = Post.createPost(PostCategory.FREE, title, content, member, files);
        Post savedPost = postRepository.save(post);

        return CreatePostResponse.of(savedPost, files);
    }

    public ModifyPostResponse modifyPost(String email, Long postId, String title, String content, List<UploadFile> newFiles, List<Long> deletedFileIds) {
        Optional<Post> findPost = postQueryRepository.findByIdWithMember(postId);
        if (findPost.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 게시물입니다.");
        }
        Post post = findPost.get();

        postWriterCheck(email, post);

        post.modify(title, content, newFiles, deletedFileIds);

        return ModifyPostResponse.of(post);
    }

    private static void postWriterCheck(String email, Post post) {
        if (!post.getMember().getEmail().equals(email)) {
            throw new AuthenticationException("게시물 작성자가 아닙니다.") {
            };
        }
    }

}
