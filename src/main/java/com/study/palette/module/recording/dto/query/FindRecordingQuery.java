package com.study.palette.module.recording.dto.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.recording.RecordingSort;
import com.study.palette.common.exception.CustomException;
import com.study.palette.module.recording.entity.QRecordingInfo;
import com.study.palette.module.recording.exception.RecordingErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FindRecordingQuery extends PageDto {

  @Schema(description = "엔지니어링 제공 여부", type = "string", allowableValues = {"ALL", "ENGINEERING",
      "NOT_ENGINEERING"})
  public RecordingSort recordingSort;

  public BooleanExpression getRecordingSort() {
    if (this.recordingSort == null) {
      throw new CustomException(RecordingErrorCode.RECORDING_NOT_SORT);
    }
    if (this.recordingSort == RecordingSort.ALL) {
      return null;
    } else if (this.recordingSort == RecordingSort.ENGINEERING) {
      return QRecordingInfo.recordingInfo.isRecordingEngineering.eq(true);
    } else if (this.recordingSort == RecordingSort.NOT_ENGINEERING) {
      return QRecordingInfo.recordingInfo.isRecordingEngineering.eq(false);
    }
    return null;
  }
}
