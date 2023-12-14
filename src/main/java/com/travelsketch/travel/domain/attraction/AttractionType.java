package com.travelsketch.travel.domain.attraction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttractionType {

    ATTRACTION(12, "관광지"),
    CULTURE(14, "문화시설"),
    FESTIVAL(15, "축제공연행사"),
    COURSE(25, "여행코스"),
    REPORT(28, "레포츠"),
    ACCOMMODATION(32, "숙박"),
    SHOPPING(38, "쇼핑"),
    FOOD(39, "음식점");

    private final int code;
    private final String text;

    public static String getText(int code) {
        for (AttractionType type : values()) {
            if (type.code == code) {
                return type.getText();
            }
        }

        throw new IllegalArgumentException();
    }
}
