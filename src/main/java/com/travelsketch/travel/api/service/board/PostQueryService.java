package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.repository.PostQueryRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostQueryService {
    private final PostQueryRepository postQueryRepository;
    private final PostRepository postRepository;

    /**
     * 게시물 목록 조회
     *
     * @param pageRequest 페이지 정보
     * @param query       검색 조건
     * @return 게시물 목록 정보
     */
    public PageResponse<SearchPostsResponse> searchByCond(PageRequest pageRequest, String query) {
        List<SearchPostsResponse> responses = postQueryRepository.findByCond(pageRequest, query);

        PageImpl<SearchPostsResponse> content = new PageImpl<>(responses, pageRequest, responses.size());

        return new PageResponse<>(content);
    }

    /**
     * 게시물 상세 조회
     *
     * @param postId 게시물id
     * @return 게시물 상세 정보
     */
    public SearchPostResponse searchByPostId(Long postId) {
        Optional<Post> findPost = postQueryRepository.findByIdWithMember(postId);
        if (findPost.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 게시물입니다.");
        }
        Post post = findPost.get();

        if (post.getIsDeleted()) {
            throw new IllegalArgumentException("삭제된 게시물입니다.");
        }

        return SearchPostResponse.of(post);
    }
}

