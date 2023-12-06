package com.travelsketch.travel.domain.attraction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_code")
    private Long id;

    @Column(name = "sido_name", nullable = false, length = 30)
    private String name;

    @Builder
    private Sido(String name) {
        this.name = name;
    }
}
