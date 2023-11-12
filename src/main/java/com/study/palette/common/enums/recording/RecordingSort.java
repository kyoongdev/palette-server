package com.study.palette.common.enums.recording;

import java.util.Arrays;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordingSort {
  //전체, 엔지니어링 지원함, 엔지니어링 지원안함
  ALL(1),
  ENGINEERING(2),
  NOT_ENGINEERING(3);

  private final int sort;

  public static RecordingSort of(String name) {
    for (RecordingSort recordingSort : RecordingSort.values()) {
      if (recordingSort.name().equals(name)) {
        return recordingSort;
      }
    }
    throw new RuntimeException("sort는 1 ~ 3 까지만 가능합니다.");
  }

  public static String[] getNames() {
    return Arrays.stream(RecordingSort.values())
        .map(Enum::name)
        .toList()
        .toArray(new String[RecordingSort.values().length]);
  }
}
