package com.travelsketch.travel.api.controller.board.response;

import com.travelsketch.travel.domain.board.AttachedFile;
import com.travelsketch.travel.domain.board.Post;
import com.travelsketch.travel.domain.board.PostCategory;
import com.travelsketch.travel.domain.board.UploadFile;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchPostResponse {

    private final Long postId;
    private final PostCategory category;
    private final String title;
    private final String content;
    private final Integer scrapCount;
    private final Integer commentCount;
    private final String writer;
    private final List<UploadFile> files;
    private final LocalDateTime createdDate;

    @Builder
    private SearchPostResponse(Long postId, PostCategory category, String title, String content, Integer scrapCount, Integer commentCount, String writer, List<UploadFile> files, LocalDateTime createdDate) {
        this.postId = postId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.writer = writer;
        this.files = files;
        this.createdDate = createdDate;
    }

    public static SearchPostResponse of(Post post) {
        return SearchPostResponse.builder()
            .postId(post.getId())
            .category(post.getCategory())
            .title(post.getTitle())
            .content(post.getContent())
            .scrapCount(post.getScrapCount())
            .commentCount(post.getCommentCount())
            .writer(post.getMember().getName())
            .files(convertToUploadFiles(post.getFiles()))
            .createdDate(post.getCreatedDate())
            .build();
    }

    private static List<UploadFile> convertToUploadFiles(List<AttachedFile> attachedFiles) {
        return attachedFiles.stream()
            .map(AttachedFile::getUploadFile)
            .collect(Collectors.toList());
    }


}
