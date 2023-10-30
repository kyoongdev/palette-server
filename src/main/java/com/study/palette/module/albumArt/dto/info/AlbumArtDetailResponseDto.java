package com.study.palette.module.albumArt.dto.info;

import com.study.palette.module.albumArt.dto.file.AlbumArtFileResponseDto;
import com.study.palette.module.albumArt.dto.license.AlbumArtLicenseInfoWithIdDto;
import com.study.palette.module.albumArt.dto.review.AlbumArtReviewResponseDto;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class AlbumArtDetailResponseDto {
    private String id;
    private String serviceName;
    private String serviceExplain;
    private String editInfo;
    private boolean serviceStatus;
    private LocalDateTime createdAt;
    private List<AlbumArtFileResponseDto> albumArtFileResponseDto;
    private List<AlbumArtReviewResponseDto> albumArtReviewResponseDto;
    private List<AlbumArtLicenseInfoWithIdDto> albumArtLicenseInfoResponseDto;


    public AlbumArtDetailResponseDto(AlbumArtInfo albumArtInfo) {
        this.id = albumArtInfo.getId().toString();
        this.serviceName = albumArtInfo.getServiceName();
        this.serviceExplain = albumArtInfo.getServiceExplain();
        this.editInfo = albumArtInfo.getEditInfo();
        this.serviceStatus = albumArtInfo.isServiceStatus();
        this.createdAt = albumArtInfo.getCreatedAt();
        this.albumArtLicenseInfoResponseDto = albumArtInfo.getAlbumArtLicenseInfo().stream().map(AlbumArtLicenseInfoWithIdDto::new).toList();
        this.albumArtReviewResponseDto = albumArtInfo.getAlbumArtReview().stream().map(AlbumArtReviewResponseDto::new).toList();
        this.albumArtFileResponseDto = albumArtInfo.getAlbumArtFile().stream().map(AlbumArtFileResponseDto::new).toList();
    }
}
