package com.study.palette.module.recording.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.palette.module.recording.dto.file.RecordingFileCreateRequestDto;
import com.study.palette.module.recording.dto.file.RecordingFileDto;
import com.study.palette.module.users.entity.Users;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RecordingFile {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(length = 256)
  private String originFileName;

  @Column(length = 256)
  private String uploadFileName;

  private int uploadFileSize;

  @Column(length = 256)
  private String url;

  private int fileMasterCode;

  @ManyToOne
  @JoinColumn(name = "recordingId")
  private RecordingInfo recording;

  @Column(length = 4)
  private String suffix;

  private boolean isUse;

  private boolean isThumbnail;

  private LocalDateTime createdAt;

  @ManyToOne
  @JoinColumn(name = "usersId")
  @JsonIgnore
  private Users users;

  @ManyToOne
  @JoinColumn(name = "recordingInfoId")
  private RecordingInfo recordingInfo;

  public static RecordingFile from(RecordingFileDto dto, RecordingInfo recordingInfo) {
    return RecordingFile.builder()
        .originFileName(dto.getOriginFileName())
        .uploadFileName(dto.getUploadFileName())
        .uploadFileSize(dto.getUploadFileSize())
        .url(dto.getUrl())
        .fileMasterCode(1)
        .suffix(dto.getSuffix())
        .isUse(true)
        .isThumbnail(dto.isThumbnail())
        .createdAt(LocalDateTime.now())
        .recordingInfo(recordingInfo)
        .build();
  }

  public static RecordingFile from(RecordingFileCreateRequestDto dto, RecordingInfo recordingInfo) {
    return RecordingFile.builder()
        .originFileName(dto.getOriginFileName())
        .uploadFileName(dto.getUploadFileName())
        .uploadFileSize(dto.getUploadFileSize())
        .url(dto.getUrl())
        .fileMasterCode(1)
        .suffix(dto.getSuffix())
        .isUse(true)
        .isThumbnail(dto.isThumbnail())
        .createdAt(LocalDateTime.now())
        .recordingInfo(recordingInfo)
        .build();
  }

  public void setRecording(RecordingInfo recordingInfo) {
    if (this.recordingInfo != null) {
      this.recordingInfo.getRecordingFile().remove(this);
    }

    this.recordingInfo = recordingInfo;

    recordingInfo.getRecordingFile().add(this);
  }
}
