package com.study.palette.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PagingDto {
    int total;
    int page;
    int limit;
    int skip;
    boolean hasPrev;
    boolean hasNext;
}
