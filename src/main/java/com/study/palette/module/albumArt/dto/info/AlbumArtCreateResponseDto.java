package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AlbumArtCreateResponseDto {
    public String id;
    public String serviceName;
    public String serviceExplain;
    public String editInfo;
    public boolean serviceStatus;
    public LocalDateTime createdAt;
    public List<AlbumArtFile> albumArtFile;
    public List<AlbumArtLicenseInfo> albumArtLicenseInfo;

    public AlbumArtCreateResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId().toString();
        this.serviceName = albumArtInfo.getServiceName();
        this.serviceExplain = albumArtInfo.getServiceExplain();
        this.editInfo = albumArtInfo.getEditInfo();
        this.serviceStatus = albumArtInfo.isServiceStatus();
        this.createdAt = albumArtInfo.getCreatedAt();
        this.albumArtFile = albumArtInfo.getAlbumArtFile();
        this.albumArtLicenseInfo = albumArtInfo.getAlbumArtLicenseInfo();
    }
}
