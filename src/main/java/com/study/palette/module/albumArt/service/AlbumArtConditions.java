package com.study.palette.module.albumArt.service;


import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AlbumArtConditions extends PageDto {

  private int saleType;
  private int customSort;

  public OrderSpecifier<?>[] getSort() {
    if (CustomSort.findCustomSort(this.customSort) == CustomSort.ALL) {
      return new OrderSpecifier[]{QAlbumArtInfo.albumArtInfo.createdAt.desc()};
    } else {
      return new OrderSpecifier[]{QAlbumArtRequest.albumArtRequest.id.count().desc()};
    }
  }

  public BooleanExpression getSaleTypeCondition(QAlbumArtInfo albumArtInfo) {
    if (AlbumArtSaleType.findAlbumArtSaleType(this.saleType) == AlbumArtSaleType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return albumArtInfo.salesType.eq(AlbumArtSaleType.findAlbumArtSaleType(this.saleType));
    }
  }
}