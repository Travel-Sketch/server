package com.travelsketch.travel.domain.post;

import com.travelsketch.travel.domain.BaseEntity;
import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 10)
    private String category;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String content;

    private int scrap_count;

    private int comment_count;

    protected Post() {
        this.scrap_count = 0;
        this.comment_count = 0;
    }

    @Builder
    private Post(Long id, Member member, String category, String title, String content, Integer scrap_count, Integer comment_count) {
        this.id = id;
        this.member = member;
        this.category = category;
        this.title = title;
        this.content = content;
        this.scrap_count = scrap_count;
        this.comment_count = comment_count;
    }


}
