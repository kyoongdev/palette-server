package com.study.palette.module.artist.dto.query;


import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class FindArtistsQuery extends PageDto {

    private int saleType;

    @Schema(description = "정렬", defaultValue = "NEW", type = "string", allowableValues = {"NEW",
        "POPULAR"})
    private CustomSort sort;

    public OrderSpecifier<?>[] getSort() {
        if (this.sort == null) {
        }

        if (this.sort == CustomSort.POPULAR) {
            return new OrderSpecifier[]{QArtistRequest.artistRequest.id.count().desc()};
        } else { // 신규등록
            return new OrderSpecifier[]{QArtistInfo.artistInfo.createdAt.desc()};
        }
    }

}
