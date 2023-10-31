package com.study.palette.module.recording.dto.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.recording.RecordingSort;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.recording.entity.QRecordingInfo;
import com.study.palette.module.recording.exception.RecordingErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindRecordingQuery extends PageDto {

  private int saleType;

  /*
  인기순 = 매출액 순(매출액이 같은 경우 판매량 순)
    → 매출액은 ‘작업 완료’(페이지명 :
         chat-seller-작업완료 전송_클릭)
         까지 완료된거래에 한해 선정

  판매량 순 = 판매량이 가장 많은 순서(판매량이 같은 경우 매출액 순)

  평점 순 = 서비스 후기 10개 이상인 서비스 중 서비스후기 평점이 가장 좋은 순서(평점이 같은 경우 매출액 순 다음 판매량 순)

  신규등록 순 = 판매글 등록이 완료된 최신순
*/
  @Schema(description = "정렬", defaultValue = "ALL", type = "int", allowableValues = {"ALL",
      "ENGINEERING", "NOT_ENGINEERING"})
  private RecordingSort sort;

  public BooleanExpression getSort() {
    if (this.sort == null) {
      throw new CustomException(RecordingErrorCode.RECORDING_NOT_SORT);
    }
    if (this.sort == RecordingSort.ALL) {
      return null;
    } else if (this.sort == RecordingSort.ENGINEERING) {
      return QRecordingInfo.recordingInfo.isRecordingEngineering.eq(true);
    } else if (this.sort == RecordingSort.NOT_ENGINEERING) {
      return QRecordingInfo.recordingInfo.isRecordingEngineering.eq(false);
    }
    return null;
  }
}
