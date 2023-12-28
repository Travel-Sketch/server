package com.travelsketch.travel.api.service.board;

import com.travelsketch.travel.api.PageResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostResponse;
import com.travelsketch.travel.api.controller.board.response.SearchPostsResponse;
import com.travelsketch.travel.domain.board.repository.PostQueryRepository;
import com.travelsketch.travel.domain.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public PageResponse<SearchPostsResponse> searchByCriteria(PageRequest pageRequest, String query) {
        List<SearchPostsResponse> responses = postQueryRepository.findByCriteria(pageRequest, query);

        PageImpl<SearchPostsResponse> content = new PageImpl<>(responses, pageRequest, responses.size());

        return new PageResponse<>(content);
    }

    public SearchPostResponse searchByPostId(Long postId) {
        return null;
    }
}

