package com.study.palette.module.recording.dto.info;

import com.study.palette.common.enums.recording.Address1;
import com.study.palette.common.enums.recording.Address2;
import com.study.palette.module.recording.entity.RecordingFile;
import com.study.palette.module.recording.entity.RecordingInfo;
import com.study.palette.module.recording.entity.RecordingLicenseInfo;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordingDetailResponseDto {

  private String id;
  private String serviceName;
  private String studioName;
  private Address1 studioAddress1;
  private Address2 studioAddress2;
  private boolean isRecordingEngineering;
  private String serviceExplain;
  private LocalDateTime createdAt;
  private List<RecordingFile> recordingFile;
  private List<RecordingLicenseInfo> recordingLicenseInfo;

  public RecordingDetailResponseDto(RecordingInfo recordingInfo) {
    this.id = recordingInfo.getId().toString();
    this.serviceName = recordingInfo.getServiceName();
    this.studioName = recordingInfo.getStudioName();
    this.studioAddress1 = recordingInfo.getStudioAddress1();
    this.studioAddress2 = recordingInfo.getStudioAddress2();
    this.isRecordingEngineering = recordingInfo.isRecordingEngineering();
    this.serviceExplain = recordingInfo.getServiceExplain();
    this.createdAt = recordingInfo.getCreatedAt();
//    this.recordingFile = recordingInfo.
//        this.recordingLicenseInfo = recordingInfo.getRecordingLicenseInfo();
  }
}
