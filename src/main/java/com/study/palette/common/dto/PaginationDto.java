package com.study.palette.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
public class PaginationDto<D> {
    private final PagingDto paging;

    //데이터 타입을 []을 사용하게 되면 DB에서 가져온 데이터를 넣기가 어려워지기 때문에 List로 변경하는 것이 좋습니다.
    private final List<D> data;
}
