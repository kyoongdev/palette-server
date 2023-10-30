package com.study.palette.module.recording.repository;

import com.study.palette.module.recording.entity.RecordingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<RecordingInfo, String> {

}
