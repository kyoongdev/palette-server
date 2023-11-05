package com.study.palette.module.recording.dto.info;

import com.study.palette.common.enums.recording.Address1;
import com.study.palette.common.enums.recording.Address2;
import com.study.palette.module.recording.dto.license.RecordingLicenseInfoCreateRequestDto;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.recording.entity.RecordingLicenseInfo;
import com.study.palette.module.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class RecordingCreateRequestDto {

  @Schema(description = "서비스명", example = "XX 스튜디오 입니다!")
  private String serviceName;
  @Schema(description = "스튜디오명", example = "녹음 스튜디오")
  private String studioName;
  @Schema(description = "스튜디오 주소 (도/특별시/광역시)", defaultValue = "", type = "int")
  private Address1 studioAddress1;
  @Schema(description = "스튜디오 주소 (시/군/구)", defaultValue = "", type = "int")
  private Address2 studioAddress2;
  @Schema(description = "녹음 엔지니어링 제공여부", example = "true")
  private boolean isRecordingEngineering;
  @Schema(description = "스튜디오 예약링크", example = "https://www.naver.com")
  private String studioReservationUrl;
  @Schema(description = "서비스 설명", example = "강동 최고급 녹음 스튜디오~!")
  private String serviceExplain;

  //    private List<RecordingFile> recordingFile = new ArrayList<>();
  private List<RecordingLicenseInfoCreateRequestDto> recordingLicenseInfo = new ArrayList<>();

  public RecordingInfo toEntity(User user) {
    RecordingInfo recordingInfo = RecordingInfo.builder()
        .serviceName(this.getServiceName())
        .studioName(this.getStudioName())
        .studioAddress1(this.getStudioAddress1())
        .studioAddress2(this.getStudioAddress2())
        .isRecordingEngineering(this.isRecordingEngineering())
        .studioReservationUrl(this.getStudioReservationUrl())
        .serviceExplain(this.getServiceExplain())
        .user(user)
        .build();

    recordingInfo.setRecordingLicenseInfo(this.getRecordingLicenseInfo().stream()
        .map(dto -> RecordingLicenseInfo.from(dto, recordingInfo))
        .toList());

    return recordingInfo;
  }
}
