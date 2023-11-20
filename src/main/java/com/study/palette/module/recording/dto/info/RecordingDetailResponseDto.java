package com.study.palette.module.recording.dto.info;

import com.study.palette.module.recording.dto.file.RecordingFileDto;
import com.study.palette.module.recording.dto.license.RecordingLicenseInfoDto;
import com.study.palette.common.enums.recording.CityCode;
import com.study.palette.common.enums.recording.RegionCode;
import com.study.palette.module.recording.entity.RecordingFile;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.users.dto.CommonUserDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordingDetailResponseDto {

  @Schema(description = "녹음 ID")
  private String id;
  @Schema(description = "서비스명")
  private String serviceName;
  @Schema(description = "스튜디오명")
  private String studioName;
  @Schema(description = "녹음 엔지니어링 여부")
  private RegionCode studioRegionCode;
  private CityCode studioCityCode;
  private boolean isRecordingEngineering;
  @Schema(description = "서비스 설명")
  private String serviceExplain;
  @Schema(description = "생성일")
  private LocalDateTime createdAt;
  @Schema(description = "녹음 파일")
  private List<RecordingFileDto> recordingFile;
  @Schema(description = "라이센스 정보")
  private List<RecordingLicenseInfoDto> recordingLicenseInfo;
  @Schema(description = "유저 정보")
  private CommonUserDto users;

  public RecordingDetailResponseDto(RecordingInfo recordingInfo) {
    this.id = recordingInfo.getId().toString();
    this.serviceName = recordingInfo.getServiceName();
    this.studioName = recordingInfo.getStudioName();
    this.studioRegionCode = recordingInfo.getStudioRegionCode();
    this.studioCityCode = recordingInfo.getStudioCityCode();
    this.isRecordingEngineering = recordingInfo.isRecordingEngineering();
    this.serviceExplain = recordingInfo.getServiceExplain();
    this.createdAt = recordingInfo.getCreatedAt();
    this.recordingFile = recordingInfo.getRecordingFile().stream()
        .map(RecordingFileDto::new).toList();
    this.recordingLicenseInfo = recordingInfo.getRecordingLicenseInfo().stream()
        .map(RecordingLicenseInfoDto::new).toList();
  }
}
