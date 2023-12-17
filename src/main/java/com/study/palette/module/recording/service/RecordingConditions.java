package com.study.palette.module.recording.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.recording.RecordingSort;
import com.study.palette.module.recording.entity.QRecordingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class RecordingConditions extends PageDto {

  @JsonIgnore
  public int recordingSort;

  public BooleanExpression getRecordingSort(QRecordingInfo recordingInfo) {
    if (RecordingSort.findRecordingSort(recordingSort) == RecordingSort.ENGINEERING) {
      return recordingInfo.isRecordingEngineering.eq(true);
    } else if (RecordingSort.findRecordingSort(recordingSort) == RecordingSort.NOT_ENGINEERING) {
      return recordingInfo.isRecordingEngineering.eq(false);
    } else {
      return Expressions.TRUE.eq(Expressions.TRUE);
    }
  }
}