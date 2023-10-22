package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
public class AlbumArtResponseDto {

    private String id;
    private String serviceName;
    private int salesType;
    private String userName;
    private String fileUrl;
    private int price;
    private int totalPrice;

    public AlbumArtResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId().toString();
        this.serviceName = albumArtInfo.getServiceName();
        this.salesType = albumArtInfo.getSalesType();
        this.userName = albumArtInfo.getUser().getName(); //TODO musician 생기면 활동명으로 바꿀수 있을듯?
//        this.fileUrl = albumArtInfo.getAlbumArtFile().get(0).getUploadFileName();
//        this.price = albumArtInfo.getAlbumArtLicenseInfo().get(0).getPrice();
    }

    public AlbumArtResponseDto(UUID id, String serviceName, int salesType, String userName, String fileUrl, int price, int totalPrice) {
        this.id = id.toString();
        this.serviceName = serviceName;
        this.salesType = salesType;
        this.userName = userName;
//        this.fileUrl = fileUrl;
        this.price = price;
        this.totalPrice = totalPrice;//TODO 추후 삭제 확인용
    }
}
