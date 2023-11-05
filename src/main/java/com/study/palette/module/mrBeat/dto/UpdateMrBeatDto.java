package com.study.palette.module.mrBeat.dto;

import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpdateMrBeatDto {

    private String serviceName;

    private int salesType;

    private int genre;

    private int mood;

    private List<CreateMrBeatLicenseInfoDto> mrBeatLicenseInfo;

    public MrBeatInfo toEntity() {
        return MrBeatInfo.builder()
                .serviceName(this.getServiceName())
                .salesType(this.getSalesType())
                .genre(this.getGenre())
                .mood(this.getMood())
                .mrBeatLicenseInfo(new ArrayList<>()).build();
    }
}
