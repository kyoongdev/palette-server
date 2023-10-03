package com.study.palette.module.albumArt.dto;

import com.study.palette.common.PaletteUtils;
import com.study.palette.module.albumArt.entity.AlbumArtInfo;
import com.study.palette.module.albumArt.entity.AlbumArtLicenseInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumArtLicenseCreateDto {
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