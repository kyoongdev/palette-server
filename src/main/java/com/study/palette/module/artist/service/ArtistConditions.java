package com.study.palette.module.artist.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.ArtistSalesType;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.artist.entity.QArtistRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class ArtistConditions extends PageDto {

  @JsonIgnore
  private int salesType;

  @JsonIgnore
  private int customSort;

  public ArtistConditions(int salesType, int customSort) {
    this.salesType = salesType;
    this.customSort = customSort;
    System.out.println("ArtistConditions 생성자 호출");
  }

  public BooleanExpression getSaleTypeCondition(QArtistInfo artistInfo) {
    if (ArtistSalesType.findArtistSalesType(this.salesType) == ArtistSalesType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return artistInfo.salesType.eq(ArtistSalesType.findArtistSalesType(this.salesType));
    }
  }

  public OrderSpecifier<?>[] getSort() {
    if (CustomSort.findCustomSort(this.customSort) == CustomSort.ALL) {
      return new OrderSpecifier[]{QArtistInfo.artistInfo.createdAt.desc()};
    } else {
      return new OrderSpecifier[]{QArtistRequest.artistRequest.id.count().desc()};
    }
  }

}
