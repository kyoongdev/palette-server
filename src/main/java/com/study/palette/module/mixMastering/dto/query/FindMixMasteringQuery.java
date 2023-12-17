package com.study.palette.module.mixMastering.dto.query;


import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.mixMastering.MixMasteringGenre;
import com.study.palette.module.mixMastering.entity.QMixMasteringInfo;
import com.study.palette.module.mixMastering.entity.QMixMasteringRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FindMixMasteringQuery extends PageDto {

  @Schema(description = "전문장르", defaultValue = "", example = "1 : BALAD, 2 : HIPHOP, 3 : TROT, 4 : DANCE, 5 : RNB, 6 : ROCK, 7 : INDI, 8 : FORK, 9 : ETC")
  public MixMasteringGenre genre;

  @Schema(description = "정렬", example = "1 : 신규등록, 2 : 인기순")
  private CustomSort customSort;

  public OrderSpecifier<?>[] getSort() {
    if (this.customSort == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QMixMasteringRequest.mixMasteringRequest.id.count().desc()};
    } else { // 신규등록
      return new OrderSpecifier[]{QMixMasteringInfo.mixMasteringInfo.createdAt.desc()};
    }
  }
}
