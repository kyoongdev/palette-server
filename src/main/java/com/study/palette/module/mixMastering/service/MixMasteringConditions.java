package com.study.palette.module.mixMastering.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class MixMasteringConditions extends PageDto {

  @JsonIgnore
  private int customSort;
  @JsonIgnore
  private int genre;

  public OrderSpecifier<?>[] getSort() {
    if (CustomSort.findCustomSort(this.customSort) == CustomSort.ALL) {
      return new OrderSpecifier[]{QMixMasteringInfo.mixMasteringInfo.createdAt.desc()};
    } else {
      return new OrderSpecifier[]{QMixMasteringRequest.mixMasteringRequest.id.count().desc()};
    }
  }

  public BooleanExpression getGenreCondition(QMixMasteringInfo mixMasteringInfo) {
    if (MixMasteringGenre.findMixMasteringGenre(this.genre) == MixMasteringGenre.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return mixMasteringInfo.genre.eq(MixMasteringGenre.findMixMasteringGenre(this.genre));
    }
  }

  public MixMasteringConditions(int genre, int customSort) {
    this.genre = genre;
    this.customSort = customSort;
    System.out.println("MixMasteringConditions 생성자 호출");
  }

  //setter
  public void setCustomSort(int customSort) {
    this.customSort = customSort;
    System.out.println("MixMasteringConditions setCustomSort 호출");
  }

  public void setGenre(int genre) {
    this.genre = genre;
    System.out.println("MixMasteringConditions setGenre 호출");
  }
}