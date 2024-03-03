package com.study.palette.module.mrBeat.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class MrBeatConditions extends PageDto {

  @JsonIgnore
  private int genreType;

  @JsonIgnore
  private int moodType;
  @JsonIgnore
  private int salesType;

  @JsonIgnore
  private int customSort;

  public MrBeatConditions(int genreType, int moodType, int salesType, int customSort) {
    this.genreType = genreType;
    this.moodType = moodType;
    this.salesType = salesType;
    this.customSort = customSort;
    System.out.println("MrBeatConditions 생성자 호출");
  }

  //setter
  public BooleanExpression getSaleTypeCondition(QMrBeatInfo mrBeatInfo) {
    if (MrBeatSalesType.findMrBeatSalesType(this.salesType) == MrBeatSalesType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return mrBeatInfo.salesType.eq(MrBeatSalesType.findMrBeatSalesType(this.salesType));
    }
  }

  public BooleanExpression getGenreTypeCondition(QMrBeatInfo mrBeatInfo) {
    if (MrBeatGenreType.findMrBeatGenreType(this.genreType) == MrBeatGenreType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return mrBeatInfo.genre.eq(MrBeatGenreType.findMrBeatGenreType(this.genreType));
    }
  }

  public BooleanExpression getMoodTypeCondition(QMrBeatInfo mrBeatInfo) {
    if (MrBeatMoodType.findMrBeatMoodType(this.moodType) == MrBeatMoodType.ALL) {
      return Expressions.TRUE.eq(Expressions.TRUE);
    } else {
      return mrBeatInfo.mood.eq(MrBeatMoodType.findMrBeatMoodType(this.moodType));
    }
  }



  public OrderSpecifier<?>[] getSort() {
    if (CustomSort.findCustomSort(this.customSort) == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QMrBeatRequest.mrBeatRequest.id.count().desc()};
    } else { // 신규등록
      return new OrderSpecifier[]{QMrBeatInfo.mrBeatInfo.createdAt.desc()};
    }
  }

}
