package com.travelsketch.travel.domain.notice;

import com.travelsketch.travel.domain.BaseEntity;
import com.travelsketch.travel.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Builder
    private Notice(String title, String content, Member member) {
        super(member);
        this.title = title;
        this.content = content;
    }

    //== 비즈니스 로직 ==//
    public Notice modify(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        super.updateLastModifiedBy(member);
        return this;
    }

    public void remove(Member member) {
        super.remove();
        super.updateLastModifiedBy(member);
    }
}
