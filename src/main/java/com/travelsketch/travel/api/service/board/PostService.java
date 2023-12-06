package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.controller.board.response.CreatePostResponse;
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

    public CreatePostResponse createPost(String email, String title, String content, String category) {
        return null;
    }


}
