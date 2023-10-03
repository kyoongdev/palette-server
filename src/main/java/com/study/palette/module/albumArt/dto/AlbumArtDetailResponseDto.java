package com.study.palette.module.albumArt.dto;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumArtDetailResponseDto {
    private String id;
    private String serviceName;
    private String serviceExplain;
    private String editInfo;
    private boolean serviceStatus;
    private String createdAt;

    public AlbumArtDetailResponseDto(AlbumArtInfo save) {
        this.id = save.getId().toString();
        this.serviceName = save.getServiceName();
        this.serviceExplain = save.getServiceExplain();
        this.editInfo = save.getEditInfo();
        this.serviceStatus = save.isServiceStatus();
//        this.createdAt = save.getCreatedAt().toString();
    }
}
