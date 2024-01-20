package com.study.palette.module.recording.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.study.palette.common.dto.PageDto;
import com.study.palette.common.enums.recording.CityCode;
import com.study.palette.common.enums.recording.RecordingSort;
import com.study.palette.common.enums.recording.RegionCode;
import com.study.palette.module.recording.entity.QRecordingInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public abstract class RecordingConditions extends PageDto {

  @JsonIgnore
  public int recordingSort;
  @JsonIgnore
  public int regionCode;
  @JsonIgnore
  public int cityCode;

  public BooleanExpression getRecordingSort(QRecordingInfo recordingInfo) {
    if (RecordingSort.findRecordingSort(this.recordingSort) == RecordingSort.ENGINEERING) {
      return recordingInfo.isRecordingEngineering.eq(true);
    } else if (RecordingSort.findRecordingSort(this.recordingSort) == RecordingSort.NOT_ENGINEERING) {
      return recordingInfo.isRecordingEngineering.eq(false);
    } else {
      return Expressions.TRUE.eq(Expressions.TRUE);
    }
  }

  public RegionCode getRegionCode(QRecordingInfo recordingInfo) {
    return RegionCode.findRegionCode(this.regionCode);
  }

  public CityCode getCityCode(QRecordingInfo recordingInfo) {
    return CityCode.findCityCode(this.regionCode, this.cityCode);
  }

  public RecordingConditions(int recordingSort, int regionCode, int cityCode) {
    this.recordingSort = recordingSort;
    this.regionCode = regionCode;
    this.cityCode = cityCode;
  }

  //Setter
  public void setRecordingSort(int recordingSort) {
    this.recordingSort = recordingSort;
  }

  public void setRegionCode(int regionCode) {
    this.regionCode = regionCode;
  }

  public void setCityCode(int cityCode) {
    this.cityCode = cityCode;
  }

}