package com.study.palette.module.albumArt.dto.info;

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
    private String userName;
    private String fileUrl;
    private int price;


    public AlbumArtResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId().toString();
        this.serviceName = albumArtInfo.getServiceName();
        this.salesType = albumArtInfo.getSalesType();
        this.userName = albumArtInfo.getUser().getName(); //TODO musician 생기면 활동명으로 바꿀수 있을듯?
//        this.fileUrl = albumArtInfo.getAlbumArtFile().get(0).getUploadFileName();
        this.price = albumArtInfo.getAlbumArtLicenseInfo().get(0).getPrice();
    }
}
