package com.study.palette.module.mixMastering.dto;

import com.study.palette.module.mixMastering.entity.MixMasteringFile;
import com.study.palette.module.mixMastering.entity.MixMasteringGenre;
import com.study.palette.module.mixMastering.entity.MixMasteringLicenseInfo;
import com.study.palette.module.mixMastering.entity.MixMasteringReview;
import lombok.Data;

import java.util.List;

@Data
public class MixMasteringDetailDto extends MixMasteringDto {

  private List<MixMasteringReview> reviews;

  private List<MixMasteringLicenseInfo> mixMasteringLicenseInfo;

  private List<MixMasteringFile> mixMasteringFile;

  private MixMasteringGenre mixMasteringGenre;
}
