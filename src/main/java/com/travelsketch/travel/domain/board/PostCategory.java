package com.travelsketch.travel.domain.board;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostCategory {
    FREE("자유게시판"),
    HOT_PLACE("핫플레이스");

    private final String text;
}
