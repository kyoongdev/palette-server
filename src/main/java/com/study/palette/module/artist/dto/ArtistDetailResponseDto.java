package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Builder
public class ArtistDetailResponseDto {

    private String id;

    private String serviceName;

    private String serviceInfo;

    private String editInfo;

    private List<ArtistFile> artistFile;

    private List<ArtistReview> artistReview;

    private long reviewAverage;

    private List<ArtistLicenseInfo> artistLicenseInfo;

    public static ArtistDetailResponseDto toEntity(ArtistInfo artistInfo) {
        return ArtistDetailResponseDto.builder()
                .id(artistInfo.getId())
                .serviceInfo(artistInfo.getServiceName())
                .editInfo(artistInfo.getEditInfo())
                .artistFile(artistInfo.getArtistFile())
                .artistReview(artistInfo.getArtistReview())
                .artistLicenseInfo(artistInfo.getArtistLicenseInfo()).build();
    }


}
