package com.study.palette.module.recording.dto.query;

import com.study.palette.common.dto.PageDto;
import com.study.palette.module.recording.service.RecordingConditions;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class FindRecordingQuery extends RecordingConditions {

  @Schema(description = "엔지니어링 제공 여부", defaultValue = "0")
  @Min(value = 0, message = "엔지니어링 제공 여부는 0 ~ 2 까지만 가능합니다.")
  public int recordingSort;
}
