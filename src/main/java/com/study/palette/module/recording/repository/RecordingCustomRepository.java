package com.study.palette.module.recording.repository;

import com.study.palette.module.albumArt.dto.info.AlbumArtResponseDto;
import com.study.palette.module.albumArt.dto.query.FindAlbumArtQuery;
import com.study.palette.module.recording.dto.info.RecordingResponseDto;
import com.study.palette.module.recording.dto.query.FindRecordingQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecordingCustomRepository {
    Page<RecordingResponseDto> findAll(FindRecordingQuery query, Pageable pageable);
}
