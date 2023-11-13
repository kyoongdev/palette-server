package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.filter.entity.FilterInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;


@Data
public class ArtistResponseDto {

    private String id;
    private String serviceName;
    private String serviceInfo;
    private String editInfo;

    private List<FilterInfo> filterInfo = new ArrayList<>();

    private boolean serviceStatus;

    private String userName;

    private List<ArtistFile> artistFile;

    private List<ArtistLicenseInfo> artistLicenseInfo;

    private List<ArtistReview> artistReview;

    public ArtistResponseDto(ArtistInfo artistInfo){
        this.id= artistInfo.getId();
        this.serviceName = artistInfo.getServiceName();
        this.serviceInfo = artistInfo.getServiceInfo();
        this.editInfo = artistInfo.getEditInfo();
        this.serviceInfo = artistInfo.getServiceInfo();
        this.serviceStatus = artistInfo.isServiceStatus();
        this.userName = artistInfo.getUsers().getName();
        this.artistFile = artistInfo.getArtistFile();
        this.artistLicenseInfo = artistInfo.getArtistLicenseInfo();
        this.artistReview = artistInfo.getArtistReview();
    }

}
