package com.study.palette.module.mrBeat.dto;

import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mrBeat.MrBeatAtmosphereType;
import com.study.palette.common.enums.mrBeat.MrBeatGenreType;
import com.study.palette.common.enums.mrBeat.MrBeatSalesType;
import com.study.palette.module.mrBeat.entity.QMrBeatInfo;
import com.study.palette.module.mrBeat.entity.QMrBeatRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindMrBeatsQuery extends PageDto {

  //장르
  @Schema(description = "장르")
  private MrBeatGenreType mrBeatGenreType;

  //분위기
  @Schema(description = "분위기")
  private MrBeatAtmosphereType mrBeatAtmosphereType;

  @Schema(description = "판매 유형")
  private MrBeatSalesType salesType;

  @Schema(description = "정렬", defaultValue = "")
  private CustomSort customSort;


  public OrderSpecifier<?>[] getSort() {
    if (this.customSort == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QMrBeatRequest.mrBeatRequest.id.count().desc()};
    } else { // 신규등록
      return new OrderSpecifier[]{QMrBeatInfo.mrBeatInfo.createdAt.desc()};
    }
  }

}