package com.travelsketch.travel.domain.board;

import com.travelsketch.travel.domain.TimeBaseEntity;
import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

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

    @Builder
    private Post(Long id, Member member, PostCategory category, String title, String content, Integer scrapCount, Integer commentCount) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
        this.scrapCount = scrapCount;
        this.commentCount = commentCount;
    }

    // 스크랩 수 증가
    public void increaseScrapCount(){
        this.scrapCount += 1;
    }

    // 스크랩 수 감소
    public void decreaseScrapCount() {
        int count = scrapCount -1;
        if (count < 0) {
            throw new IllegalArgumentException();
        }
        this.scrapCount = count;
    }

    // 댓글 수 증가
    public void increaseCommentCount(){
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
