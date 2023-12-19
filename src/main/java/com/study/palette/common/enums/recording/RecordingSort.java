package com.study.palette.common.enums.recording;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordingSort {
  //전체, 엔지니어링 지원함, 엔지니어링 지원안함
  ALL(0),
  ENGINEERING(1),
  NOT_ENGINEERING(2);

  private final int sort;

  public static RecordingSort findRecordingSort(int sort) {
    for (RecordingSort recordingSort : RecordingSort.values()) {
      if (recordingSort.sort == sort) {
        return recordingSort;
      }
    }
    throw new RuntimeException("sort는 0 ~ 2 까지만 가능합니다.");
  }

  public static String[] getNames() {
    return Arrays.stream(RecordingSort.values())
        .map(Enum::name)
        .toList()
        .toArray(new String[RecordingSort.values().length]);
  }
}
