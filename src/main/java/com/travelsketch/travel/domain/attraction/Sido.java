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
    @Column(name = "sido_id")
    private Integer id;

    @Column(nullable = false, length = 10)
    private String name;

    @Builder
    private Sido(String name) {
        this.name = name;
    }
}
