package com.study.palette.module.mrBeat.dto;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.ArtistSalesType;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mrBeat.MrBeatMoodType;
import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import com.study.palette.module.artist.entity.QArtistInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindMrBeatsQuery extends PageDto {

  //장르
  @Schema(description = "장르", defaultValue = "0")
  private int genreType;

  //분위기
  @Schema(description = "분위기", defaultValue = "0")
  private int moodType;

  @Schema(description = "판매 유형", defaultValue = "0")
  private int salesType;

  @Schema(description = "정렬", defaultValue = "0")
  private int customSort;

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
