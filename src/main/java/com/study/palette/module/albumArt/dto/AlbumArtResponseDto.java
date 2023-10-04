package com.study.palette.module.albumArt.dto;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtReview;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
public class AlbumArtResponseDto {

    private String id;
    private String serviceName;
    private int salesType;
    private String editInfo;
    private String serviceExplain;
    private boolean serviceStatus;
    private LocalDateTime createdAt;
    private String userId;
    private String fileUrl;
    private AlbumArtReview albumArtReview;
    private AlbumArtLicenseInfo albumArtLicenseInfo;

    public AlbumArtResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId().toString();
        this.serviceName = albumArtInfo.getServiceName();
        this.salesType = albumArtInfo.getSalesType();
        this.editInfo = albumArtInfo.getEditInfo();
        this.serviceExplain = albumArtInfo.getServiceExplain();
        this.serviceStatus = albumArtInfo.isServiceStatus();
        this.createdAt = albumArtInfo.getCreatedAt();
        this.userId = albumArtInfo.getUser().getName();
        this.fileUrl = albumArtInfo.getAlbumArtFile().get(0).getUploadFileName();
        this.albumArtReview = albumArtInfo.getAlbumArtReview().get(0);
        this.albumArtLicenseInfo = albumArtInfo.getAlbumArtLicenseInfo().get(0);
    }

}
