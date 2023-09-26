package com.study.palette.common.dto;

import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
//PagingDto를 아래와 같이만 사용하게 되면 PagingDto를 사용할 때마다 total,page,limit,hasPrev,hasNext를 계산해야합니다.
// 계산 방식이 틀릴 수도 있고, 계산하는 코드를 매번 작성해야하는 번거로움이 있습니다. 따라서 constructor를 통해 계산을 해주는 것이 좋습니다.
public class PagingDto {
    Long total;
    int page;
    int limit;
    boolean hasPrev;
    boolean hasNext;

    public PagingDto(Pageable pageable, Long total) {
        //현재 페이지까지의 데이터 개수가 전체 개수보다 작으면 next가 있는 로직
        this.hasNext = ((long) (pageable.getPageNumber() + 1) * pageable.getPageSize()) < total;
        //현재 페이지가 1보다 크면 이전 페이지 존재
        this.hasPrev = pageable.getPageNumber() > 1;
        this.total = total;
        this.page = pageable.getPageNumber();
        this.limit = pageable.getPageSize();
    }
}
