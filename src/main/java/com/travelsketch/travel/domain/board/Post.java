package com.travelsketch.travel.domain.board;

import com.travelsketch.travel.domain.TimeBaseEntity;
import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer scrapCount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachedFile> files = new ArrayList<>();

    @Builder
    private Post(PostCategory category, String title, String content, Integer scrapCount, Integer commentCount, Member member) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
        this.member = member;
    }

    //== 연관 관계 편의 메서드 ==//
    public static Post createPost(PostCategory category, String title, String content, Member member, List<UploadFile> files) {
        Post post = Post.builder()
            .title(title)
            .content(content)
            .category(category)
            .scrapCount(0)
            .commentCount(0)
            .member(member)
            .build();

        for (UploadFile file : files) {
            AttachedFile attachedFile = AttachedFile.builder()
                .post(post)
                .uploadFile(file)
                .build();
            post.getFiles().add(attachedFile);
        }

        return post;
    }

    public void modify(String title, String content, List<UploadFile> newFiles, List<Long> deletedFileIds) {
        this.title = title;
        this.content = content;

        if (!deletedFileIds.isEmpty()) {
            for (AttachedFile file : this.getFiles()) {
                if (deletedFileIds.contains(file.getId())) {
                    file.remove();
                }
            }
        }

        if (!newFiles.isEmpty()) {
            for (UploadFile file : newFiles) {
                AttachedFile attachedFile = AttachedFile.builder()
                    .post(this)
                    .uploadFile(file)
                    .build();
                this.getFiles().add(attachedFile);
            }
        }

    }

    // 스크랩 수 증가
    public void increaseScrapCount() {
        this.scrapCount += 1;
    }

    // 스크랩 수 감소
    public void decreaseScrapCount() {
        int count = scrapCount - 1;
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.scrapCount = count;
    }

    // 댓글 수 증가
    public void increaseCommentCount() {
        this.commentCount += 1;
    }

    // 댓글 수 감소
    public void decreaseCommentCount() {
        int count = commentCount - 1;
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.commentCount = count;
    }

}
