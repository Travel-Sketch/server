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
    @Column(name = "attraction_id")
    private Integer id;

    @Column(nullable = false)
    private Integer attractionTypeId;

    @Column(length = 100)
    private String title;

    @Column(length = 10)
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

    @Column(nullable = false, length = 2)
    private String mlevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_id")
    private Sido sido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gugun_id")
    private Gugun gugun;

    @Builder
    private Attraction(Integer attractionTypeId, String title, String zipcode, String address, String tel, String image, Double longitude, Double latitude, String mlevel, Sido sido, Gugun gugun) {
        this.attractionTypeId = attractionTypeId;
        this.title = title;
        this.zipcode = zipcode;
        this.address = address;
        this.tel = tel;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
        this.mlevel = mlevel;
        this.sido = sido;
        this.gugun = gugun;
    }
}
