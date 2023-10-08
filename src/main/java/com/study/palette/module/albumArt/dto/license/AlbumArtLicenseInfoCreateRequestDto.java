package com.study.palette.module.albumArt.dto.license;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtLicenseInfoCreateRequestDto {
    private int licenseType;
    private int price;
    private String servedFile;
    private int updateCount;
    private LocalDate period;
    private int draftCount;
    private boolean isAssign;
    private boolean isUseCommercial;
    private boolean isServeOriginFile;
    private boolean isOtherUseApproved;
}