package com.travelsketch.travel.api.controller.attraction.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AttractionResponse {

    private final Long attractionId;
    private final String title;
    private final String zipcode;
    private final String address;
    private final String tel;
    private final String image;
    private final double longitude;
    private final double latitude;

    @Builder
    public AttractionResponse(Long attractionId, String title, String zipcode, String address, String tel, String image, double longitude, double latitude) {
        this.attractionId = attractionId;
        this.title = title;
        this.zipcode = zipcode;
        this.address = address;
        this.tel = tel;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
