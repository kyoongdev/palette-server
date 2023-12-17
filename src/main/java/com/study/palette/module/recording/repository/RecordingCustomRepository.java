package com.study.palette.module.recording.repository;

import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.query.FindRecordingQuery;
import com.study.palette.module.recording.service.RecordingConditions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordingCustomRepository {

  Page<RecordingResponseDto> findAll(RecordingConditions query, Pageable pageable);
}
