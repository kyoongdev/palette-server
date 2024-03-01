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
import com.study.palette.module.artist.service.ArtistConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
public class FindArtistsQuery extends ArtistConditions {

    @Schema(description = "판매유형", defaultValue = "0")
    private int salesType;

    @Schema(description = "정렬", defaultValue = "0")
    private int customSort;

    public FindArtistsQuery(int salesType, int customSort) {
        super(salesType, customSort);
        System.out.println("FindArtistsQuery 생성자 호출");
    }

}
