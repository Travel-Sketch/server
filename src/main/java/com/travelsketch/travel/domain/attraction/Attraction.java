package com.travelsketch.travel.domain.attraction;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Attraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 50)
    private String zipcode;

    @Column(length = 100)
    private String address;

    @Column(length = 50)
    private String tel;

    @Column(length = 200)
    private String image;

    @Column(precision = 20, scale = 17)
    private Double longitude;

    @Column(precision = 20, scale = 17)
    private Double latitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_code")
    private Gugun gugun;

    @Builder
    private Attraction(String title, String zipcode, String address, String tel, String image, Double longitude, Double latitude, Sido sido, Gugun gugun) {
        this.title = title;
        this.zipcode = zipcode;
        this.address = address;
        this.tel = tel;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sido = sido;
        this.gugun = gugun;
    }
}
