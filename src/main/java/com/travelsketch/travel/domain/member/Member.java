package com.travelsketch.travel.domain.member;

import com.travelsketch.travel.domain.TimeBaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, updatable = false, length = 100)
    private String email;

    @Column(nullable = false, columnDefinition = "char(60)", length = 60)
    private String pwd;

    @Column(nullable = false, updatable = false, length = 10)
    private String name;

    @Column(nullable = false, updatable = false, columnDefinition = "char(10)", length = 10)
    private String birth;

    @Column(nullable = false, updatable = false, columnDefinition = "char(1)", length = 1)
    private String gender;

    @Column(unique = true, nullable = false, length = 10)
    private String nickname;

    @Column(insertable = false, length = 200)
    private String refreshToken;

    @Column(nullable = false, updatable = false, length = 5)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    private Member(String email, String pwd, String name, String birth, String gender, String nickname, Role role) {
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.nickname = nickname;
        this.role = role;
    }

    //== 비즈니스 로직 ==//
    public Member modifyPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    public Member modifyNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }
}
