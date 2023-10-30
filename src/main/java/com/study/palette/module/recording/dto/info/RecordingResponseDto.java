package com.study.palette.module.recording.dto.info;


import com.study.palette.module.recording.entity.RecordingInfo;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RecordingResponseDto {

    private String id;
    private String serviceName;
    private int salesType;
    private String userName;
    private String fileUrl;
    private int price;
    private long requestCount;

    public RecordingResponseDto(RecordingInfo recordingInfo) {
        this.id = recordingInfo.getId().toString();
        this.serviceName = recordingInfo.getServiceName();
        this.salesType = recordingInfo.getSalesType();
        this.userName = recordingInfo.getUser().getName(); //TODO musician 생기면 활동명으로 바꿀수 있을듯?
//        this.fileUrl = recordingInfo.getRecordingFile().get(0).getUploadFileName();
//        this.price = recordingInfo.getRecordingLicenseInfo().get(0).getPrice();
    }

    public RecordingResponseDto(UUID id, String serviceName, int salesType, String userName,
            String fileUrl, int price, long requestCount) {
        this.id = id.toString();
        this.serviceName = serviceName;
        this.salesType = salesType;
        this.userName = userName;
//        this.fileUrl = fileUrl;
        this.price = price;
        this.requestCount = requestCount;//TODO 추후 삭제 확인용
    }
}
