package com.study.palette.module.recording.dto.info;

import com.study.palette.common.enums.recording.CityCode;
import com.study.palette.common.enums.recording.RegionCode;
import com.study.palette.module.recording.entity.RecordingFile;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.recording.entity.RecordingLicenseInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordingCreateResponseDto {

  @Schema(description = "아이디")
  private String id;
  @Schema(description = "서비스명", example = "XX 스튜디오 입니다!")
  private String ServiceName;
  @Schema(description = "스튜디오명", example = "녹음 스튜디오")
  private String studioName;
  @Schema(description = "스튜디오 주소 (도/특별시/광역시)", defaultValue = "", type = "int")
  private RegionCode studioRegionCode;
  @Schema(description = "스튜디오 주소 (시/군/구)", defaultValue = "", type = "int")
  private CityCode studioCityCode;
  @Schema(description = "녹음 엔지니어링 제공여부", example = "true")
  private boolean isRecordingEngineering;
  @Schema(description = "스튜디오 예약링크", example = "https://www.naver.com")
  private String studioReservationUrl;
  @Schema(description = "서비스 설명", example = "강동 최고급 녹음 스튜디오~!")
  private String serviceExplain;
  @Schema(description = "생성일")
  private LocalDateTime createdAt;

  public List<RecordingFile> recordingFile;
  public List<RecordingLicenseInfo> recordingLicenseInfo;

  public RecordingCreateResponseDto(RecordingInfo recordingInfo) {
    this.id = recordingInfo.getId().toString();
    this.ServiceName = recordingInfo.getServiceName();
    this.studioName = recordingInfo.getStudioName();
    this.studioRegionCode = recordingInfo.getStudioRegionCode();
    this.studioCityCode = recordingInfo.getStudioCityCode();
    this.isRecordingEngineering = recordingInfo.isRecordingEngineering();
    this.studioReservationUrl = recordingInfo.getStudioReservationUrl();
    this.serviceExplain = recordingInfo.getServiceExplain();
    this.createdAt = recordingInfo.getCreatedAt();
    this.recordingFile = recordingInfo.getRecordingFile();
    this.recordingLicenseInfo = recordingInfo.getRecordingLicenseInfo();
  }
}
