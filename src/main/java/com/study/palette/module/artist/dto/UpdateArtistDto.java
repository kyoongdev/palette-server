package com.study.palette.module.artist.dto;

import com.study.palette.module.artist.dto.artistFile.CreateArtistFileDto;
import com.study.palette.module.artist.dto.artistInfo.CreateArtistInfoDto;
import com.study.palette.module.filter.entity.FilterInfo;
import lombok.Data;

import java.util.List;

@Data
public class UpdateArtistDto {

    private String id;
    private String serviceName;
    private String serviceInfo;

    private String editInfo;

    private FilterInfo salesType;

    private boolean serviceStatus;

    private String userName;

    private String userEmail;

    private List<CreateArtistFileDto> artistFileDto;

    private List<CreateArtistInfoDto> artistLicenseInfo;
}
