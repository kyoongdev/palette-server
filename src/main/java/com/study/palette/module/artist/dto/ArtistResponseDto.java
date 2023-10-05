package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistInfo;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data

public class ArtistResponseDto {

    private String id;
    private String serviceName;
    private String serviceInfo;
    private String editInfo;

    private List<FilterInfo> filterInfo = new ArrayList<>();

    private int serviceStatus;

    private String userName;

    private ArtistFile artistFile;

    private ArtistLicenseInfo artistLicenseInfo;

    private ArtistReview artistReview;

    public ArtistResponseDto(ArtistInfo artistInfo){
        this.id= artistInfo.getId();
        this.serviceName = artistInfo.getServiceName();
        this.serviceInfo = artistInfo.getServiceInfo();
        this.editInfo = artistInfo.getEditInfo();
        this.serviceInfo = artistInfo.getServiceInfo();
        this.serviceStatus = artistInfo.getServiceStatus();
        this.userName = artistInfo.getUser().getName();
        this.artistFile = artistInfo.getArtistFile().get(0);
        this.artistLicenseInfo = artistInfo.getArtistLicenseInfo().get(0);
        this.artistReview = artistInfo.getArtistReview().get(0);



    }

}
