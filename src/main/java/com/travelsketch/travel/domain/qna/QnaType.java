package com.travelsketch.travel.domain.qna;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QnaType {

    ACCOUNT("계정"), ETC("기타");

    private final String text;
}
