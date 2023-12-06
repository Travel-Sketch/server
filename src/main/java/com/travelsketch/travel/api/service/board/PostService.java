package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
import com.travelsketch.travel.domain.board.Post;
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
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public CreatePostResponse createPost(String email, String title, String content, String category) {
        Optional<Member> findMember = memberRepository.findByEmail(email);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 회원입니다.");
        }
        Member member = findMember.get();

        Post post = Post.builder()
            .title(title)
            .content(content)
            .category(category)
            .build();

        Post savedPost = postRepository.save(post);

        return CreatePostResponse.of(savedPost);
    }


}
