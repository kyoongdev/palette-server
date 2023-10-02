package com.study.palette.module.albumArt.dto;

import com.study.palette.module.albumArt.entity.AlbumArtFile;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import com.study.palette.module.albumArt.entity.AlbumArtReview;
import com.study.palette.module.filter.entity.FilterInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class AlbumArtResponseDto {

    private String id;
    private String serviceName;
    private List<FilterInfo> filterInfo = new ArrayList<>(); // saleType
    private String editInfo;
    private String serviceExplain;
    private boolean serviceStatus;
    private LocalDate createdAt;
    private String userId;
    private AlbumArtFile albumArtFile;
    private AlbumArtReview albumArtReview;
    private AlbumArtLicenseInfo albumArtLicenseInfo;

    public AlbumArtResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId();
        this.serviceName = albumArtInfo.getServiceName();
        this.editInfo = albumArtInfo.getEditInfo();
        this.serviceExplain = albumArtInfo.getServiceExplain();
        this.serviceStatus = albumArtInfo.isServiceStatus();
        this.createdAt = albumArtInfo.getCreatedAt();
        this.userId = albumArtInfo.getUser().getName();
        this.albumArtFile = albumArtInfo.getAlbumArtFile().get(0);
        this.albumArtReview = albumArtInfo.getAlbumArtReview().get(0);
        this.albumArtLicenseInfo = albumArtInfo.getAlbumArtLicenseInfo().get(0);
    }

}
