package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.entity.ArtistFile;
import com.study.palette.module.artist.entity.ArtistLicenseInfo;
import com.study.palette.module.artist.entity.ArtistReview;
import com.study.palette.module.filter.entity.FilterInfo;
import com.study.palette.module.user.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    private int totalPages;

    private int totalCounts;

}
