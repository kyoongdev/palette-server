package com.study.palette.module.albumArt.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class AlbumArtConditions extends PageDto {

  @JsonIgnore
  private int salesType;
  @JsonIgnore
  private int customSort;

  public OrderSpecifier<?>[] getSort() {
    if (CustomSort.findCustomSort(this.customSort) == CustomSort.ALL) {
      return new OrderSpecifier[]{QAlbumArtInfo.albumArtInfo.createdAt.desc()};
    } else {
      return new OrderSpecifier[]{QAlbumArtRequest.albumArtRequest.id.count().desc()};
    }
  }

  public BooleanExpression getSaleTypeCondition(QAlbumArtInfo albumArtInfo) {
    if (AlbumArtSaleType.findAlbumArtSaleType(this.salesType) == AlbumArtSaleType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return albumArtInfo.salesType.eq(AlbumArtSaleType.findAlbumArtSaleType(this.salesType));
    }
  }

  public AlbumArtConditions(int salesType, int customSort) {
    this.salesType = salesType;
    this.customSort = customSort;
    System.out.println("AlbumArtConditions 생성자 호출");
  }

  //setter
  public void setSaleType(int salesType) {
    this.salesType = salesType;
    System.out.println("AlbumArtConditions setSaleType 호출");
  }

  public void setCustomSort(int customSort) {
    this.customSort = customSort;
    System.out.println("AlbumArtConditions setCustomSort 호출");
  }
}