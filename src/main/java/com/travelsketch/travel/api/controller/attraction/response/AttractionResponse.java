package com.travelsketch.travel.api.controller.attraction.response;

import com.travelsketch.travel.domain.attraction.Attraction;
import lombok.Builder;
import lombok.Getter;

import static com.travelsketch.travel.domain.attraction.AttractionType.getText;

@Getter
public class AttractionResponse {

    private final Integer attractionId;
    private final String type;
    private final String title;
    private final String zipcode;
    private final String address;
    private final String tel;
    private final String image;
    private final double longitude;
    private final double latitude;

    @Builder
    public AttractionResponse(Integer attractionId, String type, String title, String zipcode, String address, String tel, String image, double longitude, double latitude) {
        this.attractionId = attractionId;
        this.type = type;
        this.title = title;
        this.zipcode = zipcode;
        this.address = address;
        this.tel = tel;
        this.image = image;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static AttractionResponse of(Attraction attraction) {
        return AttractionResponse.builder()
            .attractionId(attraction.getId())
            .type(getText(attraction.getAttractionTypeId()))
            .title(attraction.getTitle())
            .zipcode(attraction.getZipcode())
            .address(attraction.getAddress())
            .tel(attraction.getTel())
            .image(attraction.getImage())
            .longitude(attraction.getLongitude())
            .latitude(attraction.getLatitude())
            .build();
    }
}
