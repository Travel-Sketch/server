package com.travelsketch.travel.domain.attraction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gugun {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gugun_code")
    private Long id;

    @Column(name = "gugun_name", nullable = false, length = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @Builder
    private Gugun(String name, Sido sido) {
        this.name = name;
        this.sido = sido;
    }
}
