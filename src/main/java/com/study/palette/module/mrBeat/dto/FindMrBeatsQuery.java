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
import com.study.palette.module.mrBeat.service.MrBeatConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

public class FindMrBeatsQuery extends MrBeatConditions {

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

  public FindMrBeatsQuery(int genreType, int moodType, int salesType, int customSort) {
    super(genreType, moodType, salesType, customSort);
    System.out.println("FindMrBeatsQuery 생성자 호출");
  }

}
