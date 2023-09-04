package com.study.palette.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class PaginationDto<D> {
    private final PagingDto paging;
    private final D[] data;
}
