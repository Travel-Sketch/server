package com.travelsketch.travel.api.controller.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum DomainType {

    NAVER("naver.com"),
    GOOGLE("gmail.com"),
    KAKAO("kakao.com");

    private final String key;

    public static boolean isContainDomain(String domain) {
        return Arrays.stream(values())
            .anyMatch(value -> value.getKey().equals(domain));
    }
}
