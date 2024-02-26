package com.study.palette.module.artist.dto.query;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.ArtistSalesType;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class FindArtistsQuery extends PageDto {

    @Schema(description = "판매유형", defaultValue = "")
    private int salesType;

    @Schema(description = "정렬", defaultValue = "NEW", type = "string", allowableValues = {"NEW",
        "POPULAR"})
    private CustomSort sort;

    public BooleanExpression getSaleTypeCondition(QArtistInfo artistInfo) {
        if (ArtistSalesType.findArtistSalesType(this.salesType) == ArtistSalesType.ALL) {
            return Expressions.TRUE.eq(Expressions.TRUE);
        } else {
            return artistInfo.salesType.eq(ArtistSalesType.findArtistSalesType(this.salesType));
        }
    }

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
