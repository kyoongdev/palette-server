package com.study.palette.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CustomSort {
    POPULAR(1),
    NEW(2);

    private final int sort;

    public static CustomSort findCustomSort(int sort) {
        for (CustomSort customSort : CustomSort.values()) {
            if (customSort.sort == sort) {
                return customSort;
            }
        }
        throw new RuntimeException("sort는 1 ~ 2 까지만 가능합니다.");
    }
}
