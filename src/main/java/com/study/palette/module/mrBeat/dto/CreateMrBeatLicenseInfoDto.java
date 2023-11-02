package com.study.palette.module.mrBeat.dto;

import lombok.Data;

@Data
public class CreateMrBeatLicenseInfoDto {
    private int licenseType;

    private int price;

    private String servedFile;

//    private String isArrangement;
}
