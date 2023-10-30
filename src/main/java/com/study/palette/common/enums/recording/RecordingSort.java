package com.study.palette.common.enums.recording;

import com.study.palette.common.enums.CustomSort;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum RecordingSort {
    //전체, 엔지니어링 지원함, 엔지니어링 지원안함
    ALL(0),
    ENGINEERING(1),
    NOT_ENGINEERING(2);

    private final int sort;

    public static RecordingSort findRecordingSort(String name) {
        for (RecordingSort recordingSort : RecordingSort.values()) {
            if (recordingSort.name().equals(name)) {
                return recordingSort;
            }
        }
        throw new RuntimeException("sort는 1 ~ 2 까지만 가능합니다.");
    }


}
