package com.study.palette.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomSort {
    POPULAR("인기순"),
    RECOMMEND("추천순"),
    NEW("신규순"),
    SCORE("평점순");

    private final String name;
}
