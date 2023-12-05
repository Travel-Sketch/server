package com.travelsketch.travel.domain.qna;

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
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false, length = 10)
    private QnaType type;

    @Column(nullable = false, updatable = false, length = 50)
    private String title;

    @Lob
    @Column(nullable = false, updatable = false)
    private String content;

    @Column(updatable = false, columnDefinition = "char(4)")
    private String pwd;

    @Lob
    @Column(updatable = false)
    private String answer;

    @Builder
    private Qna(Member member, QnaType type, String title, String content, String pwd, String answer) {
        super(member);
        this.type = type;
        this.title = title;
        this.content = content;
        this.pwd = pwd;
        this.answer = answer;
    }

    //== 비즈니스 로직 ==//
    public Qna createAnswer(Member member, String answer) {
        super.updateLastModifiedBy(member);
        this.answer = answer;
        return this;
    }

    public void remove(Member member) {
        super.remove();
        super.updateLastModifiedBy(member);
    }
}
