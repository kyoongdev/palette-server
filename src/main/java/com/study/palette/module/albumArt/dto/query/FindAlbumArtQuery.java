package com.study.palette.module.albumArt.dto.query;


import com.querydsl.core.types.OrderSpecifier;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.CustomSort;
import com.study.palette.common.enums.albumArt.AlbumArtSaleType;
import com.study.palette.module.albumArt.entity.QAlbumArtInfo;
import com.study.palette.module.albumArt.entity.QAlbumArtRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindAlbumArtQuery extends PageDto {

  /*
      판매유형
      전체, 사진편집, 일러스트, 그래픽아트, 그외 장르
   */
  @Schema(description = "판매유형", defaultValue = "")
  private AlbumArtSaleType saleType;

  /*
  인기순 = 매출액 순(매출액이 같은 경우 판매량 순)
    → 매출액은 ‘작업 완료’(페이지명 :
         chat-seller-작업완료 전송_클릭)
         까지 완료된거래에 한해 선정

  판매량 순 = 판매량이 가장 많은 순서(판매량이 같은 경우 매출액 순)

  평점 순 = 서비스 후기 10개 이상인 서비스 중 서비스후기 평점이 가장 좋은 순서(평점이 같은 경우 매출액 순 다음 판매량 순)

  신규등록 순 = 판매글 등록이 완료된 최신순
*/
  @Schema(description = "정렬", defaultValue = "")
  private CustomSort customSort;

  public OrderSpecifier<?>[] getSort() {
    if (this.customSort == CustomSort.POPULAR) {
      return new OrderSpecifier[]{QAlbumArtRequest.albumArtRequest.id.count().desc()};
    } else { // 신규등록
      return new OrderSpecifier[]{QAlbumArtInfo.albumArtInfo.createdAt.desc()};
    }
  }
}