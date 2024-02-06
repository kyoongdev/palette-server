package com.study.palette.module.albumArt.dto.query;

import com.study.palette.module.albumArt.service.AlbumArtConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;

public class FindAlbumArtQuery extends AlbumArtConditions {

  /*
    판매유형
    전체, 사진편집, 일러스트, 그래픽아트, 그외 장르
 */
  @Schema(description = "판매유형", defaultValue = "0")
  @Min(value = 0, message = "판매유형은 0 ~ 4 까지만 가능합니다.")
  private int salesType;

  /*
  인기순 = 매출액 순(매출액이 같은 경우 판매량 순)
    → 매출액은 ‘작업 완료’(페이지명 :
         chat-seller-작업완료 전송_클릭)
         까지 완료된거래에 한해 선정

  판매량 순 = 판매량이 가장 많은 순서(판매량이 같은 경우 매출액 순)

  평점 순 = 서비스 후기 10개 이상인 서비스 중 서비스후기 평점이 가장 좋은 순서(평점이 같은 경우 매출액 순 다음 판매량 순)

  신규등록 순 = 판매글 등록이 완료된 최신순
*/
  @Schema(description = "정렬", defaultValue = "0")
  @Min(value = 0, message = "정렬은 0 ~ 3 까지만 가능합니다.")
  private int customSort;

  public FindAlbumArtQuery(int salesType, int customSort) {
    super(salesType, customSort);
    System.out.println("FindAlbumArtQuery 생성자 호출");
  }

  //setter
//  public void setSaleType(int saleType) {
//    super.setSaleType(saleType);
//    System.out.println("FindAlbumArtQuery setSaleType 호출");
//  }
//
//  public void setCustomSort(int customSort) {
//    super.setCustomSort(customSort);
//    System.out.println("FindAlbumArtQuery setCustomSort 호출");
//  }
}
