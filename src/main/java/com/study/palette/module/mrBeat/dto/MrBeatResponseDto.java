package com.study.palette.module.mrBeat.dto;

import com.study.palette.module.mrBeat.entity.MrBeatFile;
import com.study.palette.module.mrBeat.entity.MrBeatInfo;
import com.study.palette.module.mrBeat.entity.MrBeatLicenseInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class MrBeatResponseDto {

    private String id;

    private String serviceName;

    private int mood;

    private int salesType;

    private int genre;

    private boolean serviceStatus;

    private LocalDate createdAt;

    private List<MrBeatFile> mrBeatFileList;

    private List<MrBeatLicenseInfo> mrBeatLicenseInfo;

    public MrBeatResponseDto(MrBeatInfo mrBeatInfo) {
        this.id = mrBeatInfo.getId();
        this.serviceName = mrBeatInfo.getServiceName();
        this.mood = mrBeatInfo.getMood();
        this.salesType = mrBeatInfo.getSalesType();
        this.genre = mrBeatInfo.getGenre();
        this.serviceStatus = mrBeatInfo.isServiceStatus();
        this.createdAt = mrBeatInfo.getCreatedAt();
        this.mrBeatFileList = mrBeatInfo.getMrBeatFileList();
        this.mrBeatLicenseInfo = mrBeatInfo.getMrBeatLicenseInfo();
    }

}
